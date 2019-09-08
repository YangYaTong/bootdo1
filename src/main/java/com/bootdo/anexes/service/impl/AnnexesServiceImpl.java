package com.bootdo.anexes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.bootdo.anexes.dao.AnnexesDao;
import com.bootdo.anexes.domain.AnnexesDO;
import com.bootdo.anexes.service.AnnexesService;
import com.bootdo.anexes.util.AnnexesUtil;
import com.bootdo.common.ExportWord07Utils.MSWordTool;
import com.bootdo.common.utils.CurencyUtil;
import com.bootdo.common.utils.DateUtil;
import com.bootdo.contractMoule.dao.MouldDao;
import com.bootdo.contractMoule.domain.MouldDO;
import com.bootdo.freeContract.domain.ContractDO;
import com.bootdo.mouldTable.dao.MouldTableDao;
import com.bootdo.mouldTable.domain.MouldTableDO;
import com.bootdo.standardContract.requestVO.TableVO;
import com.bootdo.standardContract.service.ContractStandardService;
import com.bootdo.system.dao.UserDao;

@Service
public class AnnexesServiceImpl implements AnnexesService {
	@Autowired
	private AnnexesDao annexesDao;
	@Autowired
	private ContractStandardService contractService;
	@Autowired
	UserDao userDao;
	@Autowired
	private MouldDao mouldDao;// 合同模板
	@Autowired
	private MouldTableDao mouldTableDao;// 合同模板中表格内容

	@Override
	public AnnexesDO get(Integer annexesId) {
		return annexesDao.get(annexesId);
	}

	@Override
	public List<AnnexesDO> list(Map<String, Object> map) {
		return annexesDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return annexesDao.count(map);
	}

	@Override
	public int save(AnnexesDO annexes) {
		return annexesDao.save(annexes);
	}

	@Override
	public int update(AnnexesDO annexes) {
		return annexesDao.update(annexes);
	}

	@Override
	public int remove(Integer annexesId) {
		return annexesDao.remove(annexesId);
	}

	@Override
	public int batchRemove(Integer[] annexesIds) {
		return annexesDao.batchRemove(annexesIds);
	}

	@Override
	public List<AnnexesDO> listContractInfo(Integer contractId) {

		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("contractId", contractId);
		List<AnnexesDO> resultList = new ArrayList<AnnexesDO>();

		/*
		 * ContractDO contract = new ContractDO(); contract
		 * =contractDao.get(contractId);
		 * 
		 * 
		 * 
		 * //得到合同中的mainPaper（数组样式的字符串） String mainPaper = contract.getMainpaper();
		 * 
		 * @SuppressWarnings("unchecked") List<Integer> list =(List<Integer>)
		 * JSON.parse(mainPaper); for (Integer id : list) { if(annexesDao.get(id)==null)
		 * { continue; } resultList.add(annexesDao.get(id)); }
		 */

		resultList = annexesDao.list(queryMap);

		return resultList;
	}

	@Override
	public List<AnnexesDO> listByContractId(String contractId) {

		return annexesDao.listByContractId(contractId);
	}

	@Override
	public HttpServletResponse download(String annexesId, HttpServletResponse response) throws IOException {

		AnnexesDO annexes = annexesDao.get(Integer.parseInt(annexesId));

		String path = annexes.getAnnexesPath();
		// path是指欲下载的文件的路径。
		File file = new File(path);
		// 取得文件名。
		String filename = file.getName();
		System.err.println("要下载的文件附件名称--》" + filename);
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

	@Override
	public ContractDO standContractCreateAnnexes(ContractDO contractStandard, Long userId) throws Exception {
		MSWordTool tool = new MSWordTool();

		ContractDO detailContract = contractService.showContractDetails(contractStandard);
		System.err.println("前端提交的contract--"+detailContract);
		
		MouldDO mould = mouldDao.get(Integer.parseInt(contractStandard.getModelId()));
		// 模板地址
		String inputPath = mould.getMouldPath();

		String fileName = mould.getMouldName() + DateUtil.getCurrentDateTimeStr() + "."
				+ mould.getMouldPath().split("\\.")[1];
		// 输出地址
		String outputPath = "c:\\" + mould.getMouldName() + DateUtil.getCurrentDateTimeStr() + "."
				+ mould.getMouldPath().split("\\.")[1];
		// 模板中的表格标签
		String tableBookMark = mould.getTableTag();// TODO 表格标签需要添加模板时事先设置

		// 模板文本中需要替换的map
		Map<String, String> content = new HashMap<>();

		content.put("contractNO", detailContract.getContractNo());
		content.put("opppsiteCompany", detailContract.getOtherpartId());
		content.put("ourCompany", detailContract.getOurpartId());
		content.put("contractName", detailContract.getContractName());
		content.put("invatationNO", detailContract.getInvatationNo());
		content.put("supplyDate", "6日");
		content.put("disagreeDate", "10日");
		content.put("startTime", detailContract.getStartIme());
		content.put("endTime", detailContract.getEndTime());
		content.put("largeMoney", CurencyUtil.toChinaUpper(contractStandard.getCost()).toString());

		// 模板表格中需要替换的的内容
		List<Map<String, String>> tableList = new ArrayList<Map<String, String>>();


		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("mouldId", contractStandard.getModelId());
		queryMap.put("creatiedUser", userId);
		MouldTableDO mtable = mouldTableDao.getLastTableBymouldId(queryMap);
		TableVO tableVO = JSON.parseObject(mtable.getClumContent(), TableVO.class);
		Map<String, String[]> map = tableVO.toMap();

		String[] arr = mould.getMouldTable().split(",");

		int len = map.get("clum1").length;// 选取任意一列 得到表格行数
		
		//得到需要替换的表格内容的tableList
		for (int i = 0; i < len; i++) {
			Map<String, String> newtable = new HashMap<String, String>();
			char da = 'A';
			for (int j = 0; j < arr.length; j++) {
				String keyName = "clum" + j;
				char key = da++;
				String[] value = map.get(keyName);
				newtable.put(String.valueOf(key), value[i]);
			}

			tableList.add(newtable);
		}

		tool.replaceTextAndTable(inputPath, content, tableList, tableBookMark, outputPath);//替换模板书签，生成合同的文本附件

		detailContract.setMainpaper(outputPath);//生成的附件路径保存到Mainpaper

		return detailContract;
	}

	@Override
	public int saveAnnexes(ContractDO contract, Long userId) {
		// 保存合同附件信息
				AnnexesDO annexes = new AnnexesDO();
				annexes.setAnnexesDesc("word自动生成");
				annexes.setAnnexesName(contract.getContractName());
				annexes.setContractId(contract.getContractId().toString());
				annexes.setAnnexesPath(contract.getMainpaper());
				annexes.setCreatiedTime(DateUtil.getDateTime());
				annexes.setCreatiedUser(userDao.get(userId).getUsername());
				annexes.setAnnexesPath(contract.getMainpaper());
				annexes.setAnnexesType(AnnexesUtil.TYPE_DOCUMENT);//附件类型文档
				return annexesDao.save(annexes);
	}

}
