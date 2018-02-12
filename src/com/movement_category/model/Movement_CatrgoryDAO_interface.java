package com.movement_category.model;
import java.util.List;
import java.util.Set;

import com.movement.model.MovementVO;

public interface Movement_CatrgoryDAO_interface {
	
	public void insert(Movement_CategoryVO movement_catrgoryVO);
	public void update(Movement_CategoryVO movement_catrgoryVO);
	public void delete(Integer mov_cat_id);
	public Movement_CategoryVO findByPrimaryKey(Integer movement_catrgoryVO);
	public List<Movement_CategoryVO> getAll();
	// �d�߬Y�ʧ@���O�̪���@�ʧ@(�@��h)(�^�� Set)
	public Set<MovementVO> getMovementsByMovement_Catrgory(Integer movement_catrgoryVO);
	
}
