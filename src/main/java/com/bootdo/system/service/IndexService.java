package com.bootdo.system.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bootdo.system.vo.UserVO;
import org.springframework.stereotype.Service;

import com.bootdo.common.domain.Tree;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.UserDO;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface IndexService {
	
	Map<String, Object> getIndexPageDate(Long userId) throws Exception;
}
