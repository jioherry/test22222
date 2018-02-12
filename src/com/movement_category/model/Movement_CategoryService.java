package com.movement_category.model;

import java.util.List;
import java.util.Set;

import com.movement.model.MovementVO;

public class Movement_CategoryService {
	
	private Movement_CatrgoryDAO_interface dao;
	
	public Movement_CategoryService() {
		dao = new Movement_CategoryDAO();
	}
	
	// 新增
	public Movement_CategoryVO addMovement_Category (String mov_cat_name, String mov_cat_info, byte[] mov_cat_img) {
		
		Movement_CategoryVO  movement_categoryVO = new Movement_CategoryVO();
		movement_categoryVO.setMov_cat_name(mov_cat_name);
		movement_categoryVO.setMov_cat_info(mov_cat_info);
		movement_categoryVO.setMov_cat_img(mov_cat_img);
		dao.insert(movement_categoryVO);
		return movement_categoryVO;
	}

	// 修改
	public Movement_CategoryVO updateMovement_Category (Integer mov_cat_id, String mov_cat_name, 
			String mov_cat_info, byte[] mov_cat_img) {
		
		Movement_CategoryVO  movement_categoryVO = new Movement_CategoryVO();
		movement_categoryVO.setMov_cat_id(mov_cat_id);
		movement_categoryVO.setMov_cat_name(mov_cat_name);
		movement_categoryVO.setMov_cat_info(mov_cat_info);
		movement_categoryVO.setMov_cat_img(mov_cat_img);
		dao.update(movement_categoryVO);
		return movement_categoryVO;
	}
	
	// 刪除
	public void deleteMovement_Category(Integer mov_cat_id) {
		dao.delete(mov_cat_id);
	}
	
	// 查詢
	public Movement_CategoryVO getOneMovement_Category(Integer mov_cat_id) {
		return dao.findByPrimaryKey(mov_cat_id);
	}
	
	// 類別列表
	public List<Movement_CategoryVO> getAll() {
		return dao.getAll();
	}
	
	// 動作列表
	public Set<MovementVO> getMovementsByMovement_Catrgory(Integer mov_cat_id) {
		return dao.getMovementsByMovement_Catrgory(mov_cat_id);
	}
	
}
