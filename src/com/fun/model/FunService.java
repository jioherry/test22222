package com.fun.model;

import java.util.List;

public class FunService {
    private FunDAO_interface dao;
    
    
    public FunService(){
    	
    	dao = new FunDAO();
    }
    
    public FunVO addFun(String fun_name){
        
    	FunVO funVO = new FunVO();
        
        funVO.setFun_name(fun_name);
        
        dao.insert(funVO);
        
        return funVO;
    	
    }
    
    public FunVO updateFun(Integer fun_id, String fun_name){
    	
    	FunVO funVO = new FunVO();
    	
    	funVO.setFun_id(fun_id);
    	funVO.setFun_name(fun_name);
    	
    	dao.update(funVO);
    	
    	return funVO;
    	
    }
    
    public void deleteFun(Integer fun_id){
    	dao.delete(fun_id);
    	
    }
    
    public FunVO getOneFun(Integer fun_id){
    	return dao.findByPrimaryKey(fun_id);
    }
    
    public List<FunVO> getAll(){
    	return dao.getAll();
    }
    
    
    
}
