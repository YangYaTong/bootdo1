package com.bootdo.response.service.impl;

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

import com.bootdo.anexes.domain.AnnexesDO;
import com.bootdo.common.ExportWord07Utils.MSWordTool;
import com.bootdo.common.utils.CurencyUtil;
import com.bootdo.common.utils.DateUtil;
import com.bootdo.contractMoule.domain.MouldDO;
import com.bootdo.freeContract.dao.ContractDao;
import com.bootdo.freeContract.domain.ContractDO;
import com.bootdo.freeContract.service.ContractService;
import com.bootdo.payment.dao.PaymentDao;
import com.bootdo.payment.domain.PaymentDO;
import com.bootdo.response.dao.ResponseDao;
import com.bootdo.response.domain.ResponseDO;
import com.bootdo.response.service.ResponseService;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
import com.bootdo.todoTable.dao.TodotableDao;
import com.bootdo.todoTable.domain.TodotableDO;
import com.bootdo.todoTable.service.TodotableService;

@Service
public class ResponseServiceImpl implements ResponseService {
	@Autowired
	private ResponseDao responseDao;

	@Autowired
	private TodotableDao todotableDao;

	@Autowired
	private TodotableService todotableService;

	@Autowired
	private UserService userService;

	@Autowired
	private com.bootdo.payment.dao.PaymentDao paymentDao;

	@Autowired
	private ContractDao contractDao;
	
	@Autowired
	private ContractService contractService ;

	@Override
	public ResponseDO get(Integer responseId) {
		return responseDao.get(responseId);
	}

	@Override
	public List<ResponseDO> list(Map<String, Object> map) {
		return responseDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return responseDao.count(map);
	}

	
	@Override
	public int save(ResponseDO response, Integer todoTableId, Long userId, Long[] receiveUsers) {
return 0;
	}

	@Override
	public int update(ResponseDO response) {
		return responseDao.update(response);
	}

	@Override
	public int remove(Integer responseId) {
		return responseDao.remove(responseId);
	}

	@Override
	public int batchRemove(Integer[] responseIds) {
		return responseDao.batchRemove(responseIds);
	}

	@Override
	public List<ResponseDO> listContractInfo(Integer contractId) {

		List<ResponseDO> list = new ArrayList<ResponseDO>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		PaymentDO payment = paymentDao.getByContractID(contractId);
		if(payment!=null) {
			map.put("relativeId", payment.getPayId());
			
			list = responseDao.list(map);

			return list;
		}else {
			return null;
		}
		
	}

	@Override
	public int saveContractPishi(ResponseDO response, Integer todoTableId, Long userId, Long[] receiveUsers) {

		// 设置todotable的状态为已完成，添加完成时间和批示做出人
		TodotableDO todo = todotableDao.get(todoTableId);
		System.err.println("todo"+todo);
		todo.setDoneTime(DateUtil.getDateTime());
		todo.setState(2 + "");
		int row4 = 1;
		int row2=1;
		int row1=1;
		if (receiveUsers != null && !"".equals(receiveUsers)) { // 下一步提交的人员不为空,添加新的待办
			for (int i = 0; i < receiveUsers.length; i++) {
				TodotableDO todonew = new TodotableDO();
				todonew.setSendTime(DateUtil.getDateTime());
				todonew.setSendUser(userId.toString());
				todonew.setReceiveUser(receiveUsers[i].toString());
				todonew.setTodotableName(todo.getTodotableName());
				todonew.setRetiveId(todo.getRetiveId());
				todonew.setTodoType(todo.getTodoType());
				todonew.setState("0");// 设置状态为未读
				todonew.setRemark(todo.getTodotableId().toString());// 标记该待办事项的父级ID
				row4 *= todotableDao.save(todonew);
			}
		}

			// 保存批示信息

			String trueName = userService.get(userId).getName();
			response.setCreatiedUser(userId + "");
			response.setCreatiedTime(DateUtil.getDateTime());
			response.setRelativeId(todo.getRetiveId());
			response.setMaker(trueName);
			response.setResponseName(todo.getTodotableName() + "-的批示");
			response.setResponseKind(todo.getTodoType());
			// 如果没有下一步接收人，则当前的批示为最终批示，设置其parentId=-1
			if (receiveUsers == null || "".equals(receiveUsers)) {
				response.setParentId("-1");
				todo.setRemark("-1");// 标记该待办事项为顶级事项（没有下一步批示）
			}
			 row2 = responseDao.save(response);

			// 更改当前待办的事项的状态并保存
			todo.setResponseId(response.getResponseId().toString());
			
		
			 row1 = todotableService.update(todo);

			// 如果当前待办事项的类型是合同归档或者申请合同异常或者申请变更履行中的合同信息，则根据批示的结论，更改合同的相应状态
			// 如果没有下一步的接收人

			String state = "";
			ContractDO contract = contractDao.get(Integer.parseInt(todo.getRetiveId()));
			if (receiveUsers == null || "".equals(receiveUsers)) {
				// 当前待办事项的类型是合同初稿审批
				if ("contractBegin".equals(todo.getTodoType())) {
					if ("yes".equals(response.getResult())) {// 如果批示结论同意
						state = "18";
					} else {
						state = "22";
					}
				}

				// 当前待办事项的类型是请求归档
				if ("contractFinsh".equals(todo.getTodoType())) {
					if ("yes".equals(response.getResult())) {// 如果批示结论同意
						state = "95";
					} else {
						state = "90";
					}
				}
				// 当前待办事项的类型是请求合同状态异常
				if ("contractAbnormal".equals(todo.getTodoType())) {
					if ("yes".equals(response.getResult())) {// 如果批示结论同意
						state = "88";
					} else {
						state = "40";
					}
				}
				UserDO user = userService.get(userId);

				// 当前待办事项是请求变更履行中的合同信息
				if ("contractUpdateInfo".equals(todo.getTodoType())) {

					ContractDO oldContract = contractDao.get(Integer.parseInt(contract.getParentId()));
					if ("yes".equals(response.getResult())) {// 如果批示结论同意
						// 设置旧的合同状态为已经被变更，添加变更时间，变更人
						oldContract.setState("101");
						oldContract.setModifiedTime(DateUtil.getDateTime());
						oldContract.setModifiedUser(user.getName());
						contractDao.update(oldContract);
						// 设置新的合同状态为履行中，清空remark
						contract.setRemark("");
						state = "40";

					} else {
				
						// 设置旧的合同状态为履行中
						oldContract.setState("40");
						oldContract.setModifiedUser("");
						oldContract.setModifiedTime("");
						contractDao.update(oldContract);
						// 设置新的合同状态为变更未批准
						state = "83";
					}
				}

			}else {
				if ("contractBegin".equals(todo.getTodoType())) {
					state = "15";
				}else {
					state = "75";
				}
			}
			
			contract.setState(state);
			contractDao.update(contract);
			
			return row1 * row2 * row4;
		}
		
	

	@Override
	public int savePaymentPishi(ResponseDO response, Integer todoTableId, Long userId, Long[] receiveUsers) {
		// 设置todotable的状态为已完成，添加完成时间和批示做出人
		TodotableDO todo = todotableDao.get(todoTableId);
		todo.setDoneTime(DateUtil.getDateTime());
		todo.setState(2 + "");

		// 如果下一步提交的人员不为空，则 添加新的待办事项
		int row4 = 1;
		if (receiveUsers != null && !"".equals(receiveUsers)) { // 下一步提交的人员不为空
			for (int i = 0; i < receiveUsers.length; i++) {
				TodotableDO todonew = new TodotableDO();
				todonew.setSendTime(DateUtil.getDateTime());
				todonew.setSendUser(userId.toString());
				todonew.setReceiveUser(receiveUsers[i].toString());
				todonew.setTodotableName(todo.getTodotableName());
				todonew.setRetiveId(todo.getRetiveId());
				todonew.setTodoType(todo.getTodoType());
				todonew.setState("0");// 设置状态为未读
				todonew.setRemark(todo.getTodotableId().toString());// 标记该待办事项的父级ID
				row4 *= todotableDao.save(todonew);
			}
		} else { // 下一步提交的人员为空

		}

		// 保存批示信息

		String trueName = userService.get(userId).getName();
		response.setCreatiedUser(userId + "");
		response.setCreatiedTime(DateUtil.getDateTime());
		response.setRelativeId(todo.getRetiveId());
		response.setMaker(trueName);
		response.setResponseKind(todo.getTodoType());
		response.setResponseName(todo.getTodotableName() + "-的批示");
		// 如果没有下一步接收人，则当前的批示为最终批示，设置其parentId=-1
		if (receiveUsers == null || "".equals(receiveUsers)) {
			response.setParentId("-1");
		}
		int row2 = responseDao.save(response);

		// 更改当前待办的事项的状态并保存
		todo.setResponseId(response.getResponseId().toString());
		todo.setRemark("-1");// 标记该待办事项为顶级事项（没有下一步批示）
		int row1 = todotableService.update(todo);

		// 如果当前待办事项是付款类型（payment），保存合同的付款信息
		int row3 = 1;
		if ("payment".equals(todo.getTodoType())) {
			PaymentDO payment = paymentDao.get(Integer.parseInt(todo.getRetiveId()));
			payment.setChecker(userId.toString());
			if ("yes".equals(response.getResult())) {
				payment.setState("5");
				// 如果没有下一步的接收人，则添加给条付款请示的最终批准人的信息
				if (receiveUsers == null || "".equals(receiveUsers)) {
					payment.setApprover(trueName);
				}
				row3 = paymentDao.update(payment);
			} else {
				payment.setState("4");
				row3 = paymentDao.update(payment);
			}

		}	
		return row1 * row2 *row3* row4;
	}

	/**
	 * 下载合同的初稿审批签
	 */
	@Override
	public HttpServletResponse downloadResponsePaper(String contractId, HttpServletResponse response)
			throws IOException {
		//根据模板生成相应的合同审批件
		
		MSWordTool tool = new MSWordTool();
		
		List<ResponseDO> list = new ArrayList<>();
				ResponseDO res = new ResponseDO();//找到最终的审批
				list = responseDao.listByResponseKindAndRetiveId("contractBegin", contractId);
				for (ResponseDO responseDO : list) {
					if("-1".equals(responseDO.getParentId())){
						res = responseDO;
					}
				}
				
				if(list.size()==0) { //如果查询到的批示信息为空，则方法返回
					return null;
				}
				//合同审批单模板的地址
				String inputPath = "D:\\eclipse-workspace\\bootdo\\target\\classes\\static\\make\\contractMould\\contractShengpi.docx";
				//待审批的合同详情
				ContractDO detailContract = contractService.showContractDetails(contractDao.get(Integer.parseInt(contractId)));
		
		//模板地址

		
		//输出地址
		String outputPath =  "c:\\合同审批件.docx";
		
		
		//模板文本中需要替换的map
		Map<String,String> content = new HashMap<>();
		
		
		
		content.put("contractNO", detailContract.getContractNo().toString());
		content.put("oppositeCompany", detailContract.getOtherpartId().toString());
		content.put("contractName",detailContract.getContractName());
		content.put("contractCost", detailContract.getCost());
		content.put("maincontent", detailContract.getMaincontent());
		content.put("chargerName", detailContract.getCharger());
		
		content.put("leaderName2st", res.getMaker());
		content.put("leaderOpinion2st",res.getOpinion());
		content.put("leaderdate2st", res.getCreatiedTime());
		

		System.err.println("替换的文本内容"+content);
		
		
		
		
   
		tool.replaceText(inputPath, content, outputPath);
	


		//TODO   
		//下载审批件
		
		  // path是指欲下载的文件的路径。
        File file = new File(outputPath);
        // 取得文件名。
        String filename = file.getName();
        // 取得文件的后缀名。
        //String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

        // 以流的形式下载文件。
        InputStream fis = new BufferedInputStream(new FileInputStream(outputPath));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        // 清空response
        response.reset();
        
        
        // 设置response的Header
        filename = response.encodeURL(new String(filename.getBytes(),"iso8859-1"));			//保存的文件名,必须和页面编码一致,否则乱码
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
        response.addHeader("Content-Length", "" + file.length());
        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        toClient.write(buffer);
        toClient.flush();
        toClient.close();
		
		
		return response;
	}
}
