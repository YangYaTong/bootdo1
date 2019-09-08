package com.bootdo.freeContract.service.impl;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bootdo.anexes.domain.AnnexesDO;
import com.bootdo.anexes.service.AnnexesService;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.DateUtil;
import com.bootdo.common.utils.DownloadUtil;
import com.bootdo.common.utils.NumberUtil;
import com.bootdo.company.domain.CompanyDO;
import com.bootdo.company.service.CompanyService;
import com.bootdo.contractRemind.dao.RemindDao;
import com.bootdo.freeContract.DTO.ContractDTO;
import com.bootdo.freeContract.dao.ContractDao;
import com.bootdo.freeContract.domain.ContractDO;
import com.bootdo.freeContract.service.ContractService;
import com.bootdo.freeContract.util.ContractUtil;
import com.bootdo.matter.dao.MatterDao;
import com.bootdo.matter.domain.MatterDO;

import com.bootdo.ourCompany.domain.OurCompanyDO;
import com.bootdo.ourCompany.service.OurCompanyService;
import com.bootdo.project.domain.ProjectDO;
import com.bootdo.project.service.ProjectService;
import com.bootdo.system.dao.UserDao;
import com.bootdo.system.domain.UserDO;

@Service
public class ContractServiceImpl implements ContractService {
	private static final Logger logger = LoggerFactory.getLogger(ContractService.class);
	@Autowired
	private ContractDao contractDao;

	@Autowired
	private UserDao userDao;



	@Autowired
	private DictService dictService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private OurCompanyService ourCompanyService;

	@Autowired
	private MatterDao matterDao;

	@Autowired
	private RemindDao remindDao;

	@Autowired
	private AnnexesService annexesService;

	@Override
	public ContractDO get(Integer contractId) {
		return contractDao.get(contractId);
	}

	@Override
	public List<ContractDO> list(Map<String, Object> map, Long userId) throws Exception {

		// 查询没有被伪删除的 contract 且

		List<ContractDO> list = getContractByUserIdBelowState(userId, Integer.parseInt(ContractUtil.STATE_EXECUTING));
		List<ContractDO> resultList = new ArrayList<>();
		for (ContractDO contractDO : list) {
			resultList.add(showContractDetails(contractDO));
		}

		return resultList;
	}

	@Override
	public int count(Map<String, Object> map, Long userId) throws Exception {

		Map<String, Integer> mapDate = DateUtil.getCurrentYearAndMonth();

		Map<String, Object> mapquery = new HashMap<>();
		mapquery.put("myself", userId);
		mapquery.put("state", 90);// 除过状态标记为90以上的归档状态
		mapquery.put("time", mapDate.get("year"));
		mapquery.put("isDelete", 0);

		return contractDao.count(map);
	}

	@Override
	public int save(ContractDO contract, Long userId) {
		// 如果合同不需要审批，则直接设置状态为履行中 40
		if (ContractUtil.NEED_SP_NO.endsWith(contract.getNeedShengpi())) {
			contract.setState(ContractUtil.STATE_EXECUTING);
		} else {
			contract.setState(ContractUtil.STATE_DRAFT);// 状态：初稿
		}
		// 转变金额格式
		String cost = NumberUtil.changeNomarlFormat(contract.getCost());
		contract.setCost(cost);
		// 合同数据初始化
		contract = ContractUtil.initContract(contract);
		// 如果合同modelId为空或者为null 则表示该合同为非模板合同，设置其modelId =-1
		contract.setModelId(ContractUtil.NULL_MOULD);

		UserDO user = userDao.get(userId);
		contract.setCreatiedUser(user.getName());
		contract.setCreatiedTime(DateUtil.getDateTime());
		contract.setOffice(user.getDeptId().toString());
		contract.setMyself(userId.toString());
		contract.setIsDelete(ContractUtil.NEED_SP_NO);

		return contractDao.save(contract);
	}

	@Override
	public int update(ContractDO contract) {
		// 合同的签署日期如果不为空，则表示合同已经签署开始履行，设置其状态为履行中
		if (contract.getSignDate() != null && !"".equals(contract.getSignDate())) {
			contract.setState(ContractUtil.STATE_EXECUTING);
		}
		return contractDao.update(contract);
	}

	@Override
	public int remove(Integer contractId) {
		// 对合同信息进行伪删除
		ContractDO contract = contractDao.get(contractId);
		contract.setIsDelete(ContractUtil.ISDELETE_YES);
		// 删除该从头入contractID对应的合同计划,合同提醒，moneyPoll记录

		List<MatterDO> list = new ArrayList<>();
		list = matterDao.listByContractId(contractId.toString());
		Integer[] matterIds = new Integer[list.size()];

		for (int i = 0; i < list.size(); i++) {
			int k = list.get(i).getMatterId();
			matterIds[i] = k;
		}

		// 删除合同提醒
		remindDao.removeByContractId(contractId.toString());

		// 批量删除合同计划
		if (matterIds.length > 0) {
			matterDao.batchRemove(matterIds);
		}



		//
		return contractDao.update(contract);
	}

	@Override
	public int batchRemove(Integer[] contractIds) {
		// 批量伪删除
		int index = 1;
		for (Integer contractId : contractIds) {
			ContractDO contract = contractDao.get(contractId);
			contract.setIsDelete(ContractUtil.ISDELETE_YES);
			index *= contractDao.update(contract);
		}

		// 删除合同提醒
		remindDao.batchRemoveByContractIds(contractIds);

		// 根据contractIds 删除对应的合同计划
		matterDao.batchRemoveByContractIds(contractIds);


		return index;

	}

	@Override
	public List<ContractDO> fiindAll() {

		return contractDao.findAll();
	}

	@Override
	public List<ContractDO> searchlist(Map<String, Object> map) {
		// 查询没有被伪删除的 contract 且
		map.put("isDelete", ContractUtil.NEED_SP_NO);

		logger.info("==>查找条件map:" + map);

		List<ContractDO> list = contractDao.searchlist(map);
		logger.info("==>查找到的list:" + list);
		for (ContractDO contractDO : list) {
			// 合同金额转变
			contractDO.setCost(NumberUtil.changeMoneyFormat(contractDO.getCost()));
			// 合同状态转换为字符串
			//contractDO.setState(ContractUtil.getContrantStateStr(contractDO.getState()));

		}
		return list;
	}

	@Override
	public int countSearch(Map<String, Object> map) {

		return searchlist(map).size();
	}

	@Override
	public List<ContractDO> receivefindAll() {
		List<ContractDO> list = contractDao.findAll();
		List<ContractDO> resultList = new ArrayList<ContractDO>();
		for (ContractDO contractDO : list) {
			if (ContractUtil.CONTRACT_KIND_IN.equals(contractDO.getContractKind())) {
				resultList.add(contractDO);
			}
		}
		return resultList;
	}

	@Override
	public Map<String, String> gettotal(Long userId) throws Exception {
		Map<String, String> map = new HashMap<>();
		// 查询归档合同之外的合同

		Integer needPay = 0;
		Integer needReceive = 0;
		Integer payTotal = 0;
		Integer receiveTotal = 0;

		List<ContractDO> list = getContractByUserIdBelowState(userId, 40);
		System.err.println("要统计数据的List" + list);
		for (ContractDO contractDO : list) {

			if (ContractUtil.CONTRACT_KIND_IN.equals(contractDO.getContractKind())) {
				receiveTotal += Integer.parseInt(contractDO.getCost());
				needReceive += Integer.parseInt(contractDO.getNeedCost());
			} else if (ContractUtil.CONTRACT_KIND_OUT.equals(contractDO.getContractKind())) {
				payTotal += Integer.parseInt(contractDO.getCost());
				needPay += Integer.parseInt(contractDO.getNeedCost());
			}
		}

		System.err.println(needPay + "/" + needReceive + "/" + payTotal + "/" + receiveTotal);
		// 转换金额的形式
		map.put("payTotal", NumberUtil.changeMoneyFormat(payTotal.toString()));
		map.put("needPay", NumberUtil.changeMoneyFormat(needPay.toString()));
		map.put("receiveTotal", NumberUtil.changeMoneyFormat(receiveTotal.toString()));
		map.put("needReceive", NumberUtil.changeMoneyFormat(needReceive.toString()));

		return map;
	}

	@Override
	public List<ContractDO> paymentfindAll() {
		List<ContractDO> list = contractDao.findAll();
		List<ContractDO> resultList = new ArrayList<ContractDO>();
		for (ContractDO contractDO : list) {
			if (ContractUtil.CONTRACT_KIND_OUT.equals(contractDO.getContractKind())) {
				resultList.add(contractDO);
			}
		}
		return resultList;

	}

	@Override
	public int saveDTO(ContractDTO contract) {
		contract = ContractUtil.initContractDTO(contract);
		return contractDao.saveDTO(contract);
	}

	/**
	 * 用状态state查询小于此state的所有合同
	 * 
	 * @param state 关于state的含义，请查阅ContractUtil
	 * @return
	 */
	@Override
	public List<ContractDO> getIngContract(String state) {

		return contractDao.getcontractING(state);
	}

	/**
	 * 显示contract详情（给前端反馈时显示所有信息）
	 * 
	 * @param contract
	 * @return
	 */
	public ContractDO showContractDetails(ContractDO contract) {
		ContractDO contractDetail = new ContractDO();
		// 合同ID
		contractDetail.setContractId(contract.getContractId());
		// 合同名称
		if (contract.getContractName() != null && !"".equals(contract.getContractName())) {
			contractDetail.setContractName(contract.getContractName());
		} else {
			contractDetail.setContractName("");
		}
		// 合同类型
		if (contract.getContractType() != null && !"".equals(contract.getContractType())) {
			contractDetail.setContractType(dictService.getName("contract_type", contract.getContractType()));
		} else {
			contractDetail.setContractType("");
		}
		// 合同编号
		if (contract.getContractNo() != null && !"".equals(contract.getContractNo())) {
			contractDetail.setContractNo(contract.getContractNo());
		} else {
			contractDetail.setContractNo("");
		}
		// 所属项目

		if (contract.getProjectId() != null && !"".equals(contract.getProjectId())) {
			ProjectDO p = projectService.get(Integer.parseInt(contract.getProjectId()));
			// 如果能查找该条项目信息则显示项目名称，否则显示“”
			if (p == null || "".equals(p)) {
				contractDetail.setProjectId("");
			} else {
				contractDetail.setProjectId(p.getProjectname());
			}
		} else {
			contractDetail.setProjectId("");
		}

		// 对方公司
		if (contract.getOtherpartId() != null && !"".equals(contract.getOtherpartId())) {

			CompanyDO company = companyService.get(Integer.parseInt(contract.getOtherpartId()));
			if (company == null || "".equals(company)) {
				contractDetail.setOtherpartId("");
			} else {
				contractDetail.setOtherpartId(company.getName());
			}
		} else {
			contractDetail.setOtherpartId("");
		}

		// 我方公司
		if (contract.getOurpartId() != null && !"".equals(contract.getOurpartId())) {

			OurCompanyDO company = ourCompanyService.get(Integer.parseInt(contract.getOurpartId()));
			if (company == null || "".equals(company)) {
				contractDetail.setOtherpartId("");
			} else {
				contractDetail.setOurpartId(company.getName());
			}
		} else {
			contractDetail.setOurpartId("");
		}

		// 主要条款
		contractDetail.setMaincontent(contract.getMaincontent());
		// 合同金额
		contractDetail.setCost(NumberUtil.changeMoneyFormat(contract.getCost()));
		// 法律编号
		contractDetail.setAttorneyNo(contract.getAttorneyNo());
		// 开始时间
		contractDetail.setStartIme(contract.getStartIme());
		// 结束时间
		contractDetail.setEndTime(contract.getEndTime());
		// 约定履行地
		contractDetail.setPlace(contract.getPlace());

		// 付款方式
		contractDetail.setPaymentway(contract.getPaymentway());

		// 违约责任
		contractDetail.setBreach(contract.getBreach());
		// 争议解决方式

		contractDetail.setResolution(contract.getResolution());
		// 双方一至条款
		contractDetail.setAgrement(contract.getAgrement());
		// 备注
		contractDetail.setRemark(contract.getRemark());
		// 签署日期
		contractDetail.setSignDate(contract.getSignDate());
		// 父ID
		contractDetail.setParentId(contract.getParentId());
		// 标签
		contractDetail.setMyself(contract.getMyself());
		// 主要附件
		contractDetail.setMainpaper(contract.getMainpaper());
		// 模板ID
		contractDetail.setModelId(contract.getModelId());
		// 次要附件
		contractDetail.setSecondarypaper(contract.getSecondarypaper());
		// 其他附件
		contractDetail.setOtherpaper(contract.getOtherpaper());
		// 待收/付金额
		contractDetail.setNeedCost(NumberUtil.changeMoneyFormat(contract.getNeedCost()));
		// 实收/付金额
		contractDetail.setActualCost(NumberUtil.changeMoneyFormat(contract.getActualCost()));
		// 待出票额
		contractDetail.setNeedBill(NumberUtil.changeMoneyFormat(contract.getNeedBill()));
		// 实际出票额
		contractDetail.setActualBill(NumberUtil.changeMoneyFormat(contract.getActualBill()));
		// 资金进度
		contractDetail.setMoneyProgress(contract.getMoneyProgress());
		// 发票进度
		contractDetail.setBillProgress(contract.getBillProgress());
		// 负责人
		contractDetail.setCharger(contract.getCharger());

		// 负责科室
		contractDetail.setOffice(contract.getOffice());
		// 状态
		contractDetail.setState(contract.getState());
		// 创建人
		contractDetail.setCreatiedUser(contract.getCreatiedUser());
		// 创建时间
		contractDetail.setCreatiedTime(contract.getCreatiedTime());
		// 修改人
		contractDetail.setModifiedUser(contract.getModifiedUser());
		// 修改时间
		contractDetail.setModifiedTime(contract.getModifiedTime());
		// 是否需要审批
		contractDetail.setNeedShengpi(contract.getNeedShengpi());
		// 资金类型（收/付）
		if (contract.getContractKind() != null && !"".equals(contract.getContractKind())) {
			contractDetail.setContractKind(dictService.getName("contract_kind", contract.getContractKind()));
		} else {
			contractDetail.setContractKind("");
		}
		// 文件存放地点
		contractDetail.setDocumentPlace(contract.getDocumentPlace());

		return contractDetail;
	}

	@Override
	public int saveUpdate(ContractDO contract, Long userId) {
		// 如果合同不需要审批，则直接设置状态为40 履行中
		if (ContractUtil.NEED_SP_NO.endsWith(contract.getNeedShengpi())) {
			contract.setState(ContractUtil.STATE_EXECUTING);
			// 将原有的合同状态设置为被变更101
			ContractDO contractOld = contractDao.get(contract.getContractId());
			contractOld.setState(ContractUtil.STATE_UPDATE_BACKUP);
			contractDao.update(contractOld);

		} else {
			contract.setState(ContractUtil.STATE_UPDATE_UNREPORT);// 状态：变更初稿
		}
		contract.setContractId(null);
		// 转变金额格式
		String cost = NumberUtil.changeNomarlFormat(contract.getCost());
		contract.setCost(cost);
		// 合同数据初始化
		contract = ContractUtil.initContract(contract);
		// 如果合同modelId为空或者为null 则表示该合同为非模板合同，设置其modelId =-1
		contract.setModelId(ContractUtil.NULL_MOULD);

		UserDO user = userDao.get(userId);
		contract.setCreatiedUser(user.getName());
		contract.setCreatiedTime(DateUtil.getDateTime());
		contract.setOffice(user.getDeptId().toString());
		contract.setMyself(userId.toString());

		return contractDao.save(contract);
	}

	@Override
	public List<ContractDO> queryByUserIdAndState(Map<String, Object> map, Long userId) throws Exception {
		// 查询没有被伪删除的 contract 且

		List<ContractDO> list = getContractByUserIdAndStateRange(userId, 40, 90);
		List<ContractDO> resultList = new ArrayList<>();

		for (ContractDO contractDO : list) {
			resultList.add(showContractDetails(contractDO));
		}

		return resultList;
	}

	/**
	 * 查询当前用户的归档合同
	 */
	@Override
	public List<ContractDO> listfinshContractByUserId(Map<String, Object> map, Long userId) throws Exception {

		List<ContractDO> list = getContractByUserIdAndStateRange(userId, 95, 95);

		return list;
	}

	@Override
	public int updateState(Integer contractId, String state, String label) {
		ContractDO contract = contractDao.get(contractId);
		contract.setState(state);
		contract.setRemark(label);

		return contractDao.update(contract);
	}

	@Override
	public int contractUpdateNeedShengPi(ContractDO contract, String label, Long userId) {
		// 将变更前的合同信息的remark设置为 label的值,状态设置为待上报
		ContractDO oldContract = contractDao.get(contract.getContractId());
		oldContract.setRemark(label);
		oldContract.setState(ContractUtil.STATE_UPDATE_UNAPPAOVAL);
		int row1 = contractDao.update(oldContract);

		// 保存变更后的合同信息为初稿
		contract.setParentId(oldContract.getContractId().toString());
		contract.setContractId(null);
		contract.setRemark(label);
		contract.setState(ContractUtil.STATE_UPDATE_UNREPORT);// 设置新的合同状态为变更初稿（待审批后启用）
		UserDO user = userDao.get(userId);
		contract.setCreatiedUser(user.getName());
		contract.setCreatiedTime(DateUtil.getDateTime());
		contract.setOffice(user.getDeptId().toString());
		contract.setMyself(userId.toString());
		contract.setIsDelete(ContractUtil.ISDELETE_NO);
		// 合同数据初始化
		contract = ContractUtil.initContract(contract);
		int row2 = contractDao.save(contract);

		return row1 * row2;
	}

	/**
	 * 根据合同Id下载首各主要附件
	 */
	@Override
	public HttpServletResponse download(String contractId, HttpServletResponse response) throws IOException {

		List<AnnexesDO> list = annexesService.listByContractId(contractId);
		// TODO 测试只看第一个附件
		String path = "";
		// String fileName ="";
		for (int i = 0; i < list.size(); i++) {
			path = list.get(0).getAnnexesPath();
			// fileName=list.get(0).getAnnexesName();

		}

		// path是指欲下载的文件的路径。
		File file = new File(path);
		// 取得文件名。
		String filename = file.getName();
		// 取得文件的后缀名。
		// String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

		// 以流的形式下载文件。
		InputStream fis = new BufferedInputStream(new FileInputStream(path));
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		// 清空response
		response.reset();

		// 设置response的Header
		filename = response.encodeURL(new String(filename.getBytes(), "iso8859-1")); // 保存的文件名,必须和页面编码一致,否则乱码
		response.addHeader("Content-Disposition", "attachment;filename=" + filename);
		response.addHeader("Content-Length", "" + file.length());
		OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
		response.setContentType("application/octet-stream");
		toClient.write(buffer);
		toClient.flush();
		toClient.close();

		return response;
	}

	/**
	 * 根据用户Id和state查询比此state小的合同
	 */
	public List<ContractDO> getContractByUserIdBelowState(Long userId, Integer state) throws Exception {

		Map<String, Object> mapquery = new HashMap<>();
		mapquery.put("myself", userId);
		mapquery.put("state", state);
		mapquery.put("time", DateUtil.getCurrentYearAndMonth().get("year"));
		mapquery.put("isDelete", ContractUtil.NEED_SP_NO);

		List<ContractDO> list = contractDao.searchFulfillingContractByUserId(mapquery);
		return list;

	}

	/**
	 * 根据用户Id和state的上限值、下限值查询state在区间内的合同
	 * 
	 * @param userId--当前用户的Id
	 * @param stateMin--合同当前所处的状态下限（可以查询com.bootdo.freeContract.util.ContractUtil）
	 * @param stateMax--合同当前所处的状态上限（可以查询com.bootdo.freeContract.util.ContractUtil）
	 * @return 封装了contract的信息的list
	 * 
	 */
	public List<ContractDO> getContractByUserIdAndStateRange(Long userId, Integer stateMin, Integer stateMax)
			throws Exception {

		Map<String, Object> map = new HashMap<>();
		map.put("myself", userId);
		map.put("isDelete", ContractUtil.NEED_SP_NO);
		map.put("stateMin", stateMin);
		map.put("stateMax", stateMax);
		map.put("time", DateUtil.getCurrentYearAndMonth().get("year"));
		List<ContractDO> list = contractDao.queryByUserIdAndState(map);

		return list;

	}

	/**
	 * 根据userId和state，查询用户所在的部门合同信息
	 * 
	 * @param userId--当前用户的Id
	 * @param state--合同当前所处的状态（可以查询com.bootdo.freeContract.util.ContractUtil）
	 * @return 封装了contract的信息的list
	 */
	public List<ContractDO> getDeptContractByUserIdBelowState(Long userId, Integer state) throws Exception {

		Long deptId = userDao.get(userId).getDeptId();

		Map<String, Object> mapquery = new HashMap<>();
		mapquery.put("state", state);// 除过状态标记为90以上的归档状态
		mapquery.put("time", DateUtil.getCurrentYearAndMonth().get("year"));
		mapquery.put("isDelete", ContractUtil.NEED_SP_NO);
		mapquery.put("deptId", deptId);

		List<ContractDO> list = contractDao.searchFulfillingContractByUserId(mapquery);

		return list;

	}

	/**
	 * 显示合同列表的详细信息（一些合同要素中的Id转换为对应的name，增加可读性）
	 * 
	 * @param list 原始的list
	 * @return 封装了contract的信息的list
	 */
	@Override
	public List<ContractDO> showContractDetailList(List<ContractDO> list) {
		List<ContractDO> resultList = new ArrayList<>();
		for (ContractDO contractDO : list) {
			resultList.add(showContractDetails(contractDO));
		}

		return resultList;
	}

	@Override
	public Map<String, String> showMoneyDetails(Integer contractId) {

		ContractDO contract = contractDao.get(contractId);
		Integer cost = Integer.parseInt(contract.getCost());// 合同金额
		Integer actualCost = Integer.parseInt(contract.getActualCost());// 实际收付金额
		Integer actualBill = Integer.parseInt(contract.getActualBill());// 实际收付票额

		List<MatterDO> matterList = matterDao.listByContractId(contractId.toString());
		Integer planCost = 0;// 已经计划金额
		Integer planBill = 0;// 已经计划票额

		if (matterList != null) {// 如果该合同存在合同计划事项
			for (MatterDO matter : matterList) {
				planCost += Integer.parseInt(matter.getMatterCost());
				planBill += Integer.parseInt(matter.getBillCost());
			}
		}
		// 待计划金额、票额
		Integer needPlanCost = cost - actualCost - planCost;
		Integer needPlanBill = cost - actualBill - planBill;

		Map<String, String> map = new HashMap<>();
		map.put("cost", cost.toString());
		map.put("actualCost", actualCost.toString());
		map.put("actualBill", actualBill.toString());
		map.put("planCost", planCost.toString());
		map.put("planBill", planBill.toString());
		map.put("needPlanCost", needPlanCost.toString());
		map.put("needPlanBill", needPlanBill.toString());
		map.put("contractKind", contract.getContractKind());// 合同的资金类型
		return map;
	}

	@Override
	public List<ContractDO> getExecutingListOfContractKindIn(Long userId) {
		Map<String, Object> mapquery = new HashMap<>();
		String[] states = { ContractUtil.STATE_EXECUTING, ContractUtil.STATE_APPROVAL_OK, ContractUtil.STATE_DRAFT,
				ContractUtil.STATE_IN_APPROVAL };
		mapquery.put("states", states);// 状态为初稿 ，审批中，审批通过，履行中的合同
		mapquery.put("isDelete", ContractUtil.NEED_SP_NO);
		mapquery.put("myself", userId);
		mapquery.put("contractKind", ContractUtil.CONTRACT_KIND_IN);
		List<ContractDO> list = new ArrayList<>();
		list = contractDao.queryByUserIdAndStateArray(mapquery);

		return list;
	}

	@Override
	public List<ContractDO> getExecutingListOfContractKindOut(Long userId) {
		Map<String, Object> mapquery = new HashMap<>();
		String[] states = { ContractUtil.STATE_EXECUTING, ContractUtil.STATE_APPROVAL_OK, ContractUtil.STATE_DRAFT,
				ContractUtil.STATE_IN_APPROVAL };
		mapquery.put("states", states);// 状态为初稿 ，审批中，审批通过，履行中的合同
		mapquery.put("isDelete", ContractUtil.NEED_SP_NO);
		mapquery.put("myself", userId);
		mapquery.put("contractKind", ContractUtil.CONTRACT_KIND_OUT);

		List<ContractDO> list = new ArrayList<>();
		list = contractDao.queryByUserIdAndStateArray(mapquery);

		return list;
	}

	@Override
	public void exportExcel(Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) throws IOException {
		//linux下jdk1.8 方法获取时，不会拼接自己写的目录 
		//String path = request.getSession().getServletContext().getRealPath("/") + "/static/make/xlsprint/";
		
			//寻找导出模板路径
			String path2 = ResourceUtils.getURL("classpath:").getPath()+"/static/make/xlsprint/";
			File myPath = new File(path2);
			  if (!myPath.exists()){//若此目录不存在，则创建之// 这个东西只能简历一级文件夹，两级是无法建立的。。。。。
				  myPath.mkdir();
		           System.out.println("创建文件夹路径为："+ path2 );
		}
			InputStream is = new FileInputStream(new File(path2+ "CONTRACT_LIST_EXECL.xls"));
			System.err.println("path2"+path2);
			
			
			//操作模板
			Workbook wb = new HSSFWorkbook(is);		//打开一个模板文件，工作簿
			Sheet sheet = wb.getSheetAt(0);			//获取到第一个工作表
			
			Row nRow = null;
			Cell nCell = null;
			int rowNo = 0;							//行号
			int colNo = 1;							//列号
			
			
			//获取模板上的单元格样式
			nRow = sheet.getRow(2);
			
			//序号的样式
			nCell = nRow.getCell(0);
			CellStyle indexStyle = nCell.getCellStyle();		
			
			//合同名的样式
			nCell = nRow.getCell(1);
			CellStyle nameStyle = nCell.getCellStyle();		
			
			//类型的样式
			nCell = nRow.getCell(2);
			CellStyle contractTypeStyle = nCell.getCellStyle();		
			
			//资金类型的样式
			nCell = nRow.getCell(3);
			CellStyle contractKindStyle = nCell.getCellStyle();		
			
			//金额的样式
			nCell = nRow.getCell(4);
			CellStyle costStyle = nCell.getCellStyle();		
			
			//开始时间的样式
			nCell = nRow.getCell(5);
			CellStyle startdateStyle = nCell.getCellStyle();		
			
			//结束时间的样式
			nCell = nRow.getCell(6);
			CellStyle enddateStyle = nCell.getCellStyle();	
			
			//资金进度的样式
			nCell = nRow.getCell(7);
			CellStyle moneyProgressStyle = nCell.getCellStyle();	
			
			
			//发票进度的样式
			nCell = nRow.getCell(8);
			CellStyle billProgressStyle = nCell.getCellStyle();	
			
			//负责人的样式
			nCell = nRow.getCell(9);
			CellStyle charagerStyle = nCell.getCellStyle();	
			
			//负责科室的样式
			nCell = nRow.getCell(10);
			CellStyle officeStyle = nCell.getCellStyle();	
				
					
			
			//处理大标题
			nRow = sheet.getRow(rowNo++);			//获取一个行对象
			nCell = nRow.getCell(colNo);			//获取一个单元格对象
			nCell.setCellValue("合同列表");		//yyyy-MM
			
			rowNo++;								//跳过静态表格头
			

			//查询列表数据
			System.out.println("param------>"+params);
		
	        List<ContractDO> contractList = contractDao.searchlist(params);
	   
			int total = contractList.size();
			System.out.println("要打印统计条数-"+total);
			System.err.println("要打印的合同列表-"+contractList);
			for(int j=0;j<contractList.size();j++){
				colNo = 0;				//初始化
				ContractDO contract = contractList.get(j);
				nRow = sheet.createRow(rowNo++);
				nRow.setHeightInPoints(30);
				
				nCell = nRow.createCell(colNo++);
				int n=j;
				nCell.setCellValue(n+1);
				nCell.setCellStyle(indexStyle);
				                                                              
				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(contract.getContractName());
				nCell.setCellStyle(nameStyle);
				
				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(contract.getContractType());
				nCell.setCellStyle(contractTypeStyle);
				
				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(contract.getContractKind());
				nCell.setCellStyle(contractKindStyle);
				
				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(contract.getCost());
				nCell.setCellStyle(costStyle);
				
				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(contract.getStartIme());
				nCell.setCellStyle(startdateStyle);
				   
				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(contract.getEndTime());
				nCell.setCellStyle(enddateStyle);
				
				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(contract.getMoneyProgress());
				nCell.setCellStyle(moneyProgressStyle);
				
				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(contract.getBillProgress());
				nCell.setCellStyle(billProgressStyle);
				
				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(contract.getCharger());
				nCell.setCellStyle(charagerStyle);
				
				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(contract.getOffice());
				nCell.setCellStyle(officeStyle);
			}
			
		/*	OutputStream os = new FileOutputStream("c:\\outproduct.xls");
		wb.write(os);
			
			os.flush();
			os.close();*/
			
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			wb.write(os);
			
			DownloadUtil downloadUtil = new DownloadUtil();				//直接弹出下载框，用户可以打开，可以保存
			downloadUtil.download(os, response, "合同列表.xls");
			
		
		
	}

	@Override
	public String getContractNameById(String contractId) {
		if(contractId==null||"".equals(contractId)) {
			return "";
		}
		
		ContractDO contract = contractDao.get(Integer.parseInt(contractId));
		if(contract==null) {
			return "";
		}else {
			return contract.getContractName();
		}
		
	
	}

}
