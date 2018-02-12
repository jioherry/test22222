package com.movement.model;

import java.util.*;

public interface MovementDAO_interface {

	public void insert(MovementVO movementVo);
	public void update(MovementVO movementVo);
	public void delete(String mov_id);
	public MovementVO findByPrimaryKey(String mov_id);
	public List<MovementVO> getAll();
//	萬用複合查詢(傳入參數型態Map)(回傳 List)
	public List<MovementVO> getAll(Map<String, String[]> map); 
}
