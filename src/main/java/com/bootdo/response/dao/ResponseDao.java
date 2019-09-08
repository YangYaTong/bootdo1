package com.bootdo.response.dao;

import com.bootdo.response.domain.ResponseDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-25 09:25:19
 */
@Mapper
public interface ResponseDao {

	ResponseDO get(Integer responseId);
	
	List<ResponseDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ResponseDO response);
	
	int update(ResponseDO response);
	
	int remove(Integer response_id);
	
	int batchRemove(Integer[] responseIds);
	
	List<ResponseDO> listByResponseKindAndRetiveId(String responseKind,String relativeId);
	

}
