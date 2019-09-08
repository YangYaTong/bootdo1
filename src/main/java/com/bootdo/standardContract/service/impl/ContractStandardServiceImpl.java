package com.bootdo.standardContract.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.bootdo.anexes.dao.AnnexesDao;
import com.bootdo.anexes.service.AnnexesService;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.DateUtil;
import com.bootdo.common.utils.NumberUtil;

import com.bootdo.company.domain.CompanyDO;
import com.bootdo.company.service.CompanyService;
import com.bootdo.contractMoule.domain.MouldDO;
import com.bootdo.contractMoule.service.MouldService;

import com.bootdo.freeContract.dao.ContractDao;
import com.bootdo.freeContract.domain.ContractDO;
import com.bootdo.freeContract.util.ContractUtil;
import com.bootdo.mouldTable.dao.MouldTableDao;
import com.bootdo.mouldTable.domain.MouldTableDO;
import com.bootdo.ourCompany.domain.OurCompanyDO;
import com.bootdo.ourCompany.service.OurCompanyService;
import com.bootdo.project.domain.ProjectDO;
import com.bootdo.project.service.ProjectService;
import com.bootdo.standardContract.requestVO.TableVO;
import com.bootdo.standardContract.service.ContractStandardService;
import com.bootdo.system.dao.UserDao;

@Service
public class ContractStandardServiceImpl implements ContractStandardService {
	@Autowired
	private ContractDao contractDao;

	@Autowired
	private MouldService mouldService;

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
	private MouldTableDao mouldTableDao;

	@Autowired
	private AnnexesService annexesService;

	@Autowired
	AnnexesDao annexesDao;

	@Override
	public ContractDO get(Integer contractId) {
		return contractDao.get(contractId);
	}

	@Override
	public List<ContractDO> list(Map<String, Object> map) {
		return contractDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return contractDao.count(map);
	}

	@Override
	public int save(ContractDO contractStandard, Long userId) throws Exception {

		// 由模板信息添加合同信息

		ContractDO contract = fromMouldToContractDO(contractStandard, userId);

		// 生成合同附件

		ContractDO contractdetail = annexesService.standContractCreateAnnexes(contractStandard, userId);

		// 保存合同信息
		contract.setMainpaper(contractdetail.getMainpaper());
		int row = contractDao.save(contract);

		// 保存附件信息

		int row2 = annexesService.saveAnnexes(contract, userId);

		return row * row2;
	}

	@Override
	public int update(ContractDO contractStandard) {
		return contractDao.update(contractStandard);
	}

	@Override
	public int remove(Integer contractId) {
		return contractDao.remove(contractId);
	}

	@Override
	public int batchRemove(Integer[] contractIds) {
		return contractDao.batchRemove(contractIds);
	}

	@Override
	public List<String> getTableClumNameOfMould(Integer mouldId, HttpServletRequest request) throws IOException {
		MouldDO mould = mouldService.get(mouldId);

		String mouldTable = mould.getMouldTable();

		List<String> resultList = new ArrayList<String>();
		String[] list = mouldTable.split(",");
		for (String string : list) {
			resultList.add(string);
		}

		return resultList;
	}

	public ContractDO fromMouldToContractDO(ContractDO contract, Long userId) {

		MouldDO mould = mouldService.get(Integer.parseInt(contract.getModelId()));
		// 转变金额格式
		String cost = NumberUtil.changeNomarlFormat(contract.getCost());
		contract.setCost(cost);
		// 合同数据初始化
		contract = ContractUtil.initContract(contract);
		// 如果合同不需要审批，则直接设置状态为履行中 40
		if (ContractUtil.NEED_SP_NO.equals(contract.getNeedShengpi())) {
			contract.setState(ContractUtil.STATE_EXECUTING);// 状态：履行中
		} else {
			contract.setState(ContractUtil.STATE_DRAFT);// 状态：初稿
		}
		contract.setBreach(mould.getBreach());
		contract.setPaymentway(mould.getPaymentway());
		contract.setResolution(mould.getResolution());
		contract.setAgrement(mould.getAgrement());
		contract.setRemark(mould.getRemark());
		contract.setContractKind(mould.getContractKind());
		contract.setContractType(mould.getContractType());
		contract.setCreatiedUser(userDao.get(userId).getName());
		contract.setCreatiedTime(DateUtil.getDateTime());
		contract.setOffice(userDao.get(userId).getDeptId().toString());
		contract.setMyself(userId.toString());
		contract.setIsDelete(ContractUtil.ISDELETE_NO);

		// 根据mould找到最近创建的模板附表内容
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("mouldId", contract.getModelId());
		queryMap.put("creatiedUser", userId);
		MouldTableDO mtable = mouldTableDao.getLastTableBymouldId(queryMap);

		TableVO table = JSON.parseObject(mtable.getClumContent(), TableVO.class);// json格式的字符串转变为对象
		Map<String, String[]> map = table.toMap();

		Map<String, String> resultMap = new HashMap<>();
		String[] arr = mould.getMouldTable().split(",");
		if (arr.length != 0 && map.size() == arr.length) {
			for (int i = 0; i <= arr.length; i++) {
				String keyname = "clum" + i;
				resultMap.put(arr[i], Arrays.toString(map.get(keyname)));
			}
		}

		contract.setMaincontent(resultMap.toString());// 保存合同的主要内容

		return contract;
	}

	/**
	 * 合同记录的初始化
	 * 
	 * @param contract contract中的金额必须是普通的数字形式
	 * @return
	 */
	public static ContractDO initContract(ContractDO contract) {
		// 判断合同的实际收款记录，实际出票记录，计划付款记录，计划出票记录是否为空或者为null，如果是则设置其值为0
		if (contract.getActualCost() == null || "".equals(contract.getActualCost())) {
			contract.setActualCost("0");
		}

		if (contract.getActualBill() == null || "".equals(contract.getActualBill())) {
			contract.setActualBill("0");
		}

		if (contract.getBillProgress() == null || "".equals(contract.getBillProgress())) {
			contract.setBillProgress("0%");
		}

		if (contract.getMoneyProgress() == null || "".equals(contract.getMoneyProgress())) {
			contract.setMoneyProgress("0%");
		}
		if (contract.getNeedCost() == null || "".equals(contract.getNeedCost())) {
			contract.setNeedCost(contract.getCost());
		}

		if (contract.getNeedBill() == null || "".equals(contract.getNeedBill())) {
			contract.setNeedBill(contract.getCost());
		}
		return contract;

	}

	/**
	 * 显示contract详情
	 * 
	 * @param contract
	 * @return
	 */
	@Override
	public ContractDO showContractDetails(ContractDO contract) {

		ContractDO contractDetail = new ContractDO();
		// 合同ID
		contractDetail.setContractId(contract.getContractId());
		// 合同名称
		contractDetail.setContractName(contract.getContractName());
	
		// 合同类型
		contractDetail.setContractType(dictService.getName("contract_type", contract.getContractType()));

		// 合同编号
		contractDetail.setContractNo(contract.getContractNo());

		// 所属项目
		contractDetail.setProjectId(contract.getProjectId()==null?"":projectService.getProjectName(Integer.parseInt(contract.getProjectId())));

		// 对方公司
		contractDetail.setOtherpartId(companyService.getCompanyName(Integer.parseInt(contract.getOtherpartId())));
		// 我方公司
		contractDetail.setOurpartId(ourCompanyService.getCompanyName(Integer.parseInt(contract.getOurpartId())));
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
		contractDetail.setState(ContractUtil.getContrantStateStr(contract.getState()));
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
		contractDetail.setContractKind(dictService.getName("contract_kind", contract.getContractKind()));
		// 文件存放地点
		contractDetail.setDocumentPlace(contract.getDocumentPlace());
		// 招标文件标号
		contractDetail.setInvatationNo(contract.getInvatationNo());

		return contractDetail;
	}

}
