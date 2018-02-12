package com.movement.model;

import java.util.List;
import java.util.Map;

public class MovementService {
	private MovementDAO_interface dao;
	
	public MovementService() {
		dao = new MovementDAO();
	}
	// �s�W
	public MovementVO addMovement(Integer mov_cat_id, String mov_name, String mov_info, 
			byte[] mov_img, String  mov_level, Integer mov_time_length, String  mov_video) {
		
		MovementVO movementVO = new MovementVO();
		movementVO.setMov_cat_id(mov_cat_id);
		movementVO.setMov_name(mov_name);
		movementVO.setMov_info(mov_info);
		movementVO.setMov_img(mov_img);
		movementVO.setMov_level(mov_level);
		movementVO.setMov_time_length(mov_time_length);
		movementVO.setMov_video(mov_video);
		dao.insert(movementVO);
		return movementVO;
	}
	// �ק�
	public MovementVO updateMovement(String mov_id, Integer mov_cat_id, String mov_name, String mov_info, 
			byte[] mov_img, String  mov_level, Integer mov_time_length, String  mov_video) {
		
		MovementVO movementVO = new MovementVO();
		movementVO.setMov_id(mov_id);
		movementVO.setMov_cat_id(mov_cat_id);
		movementVO.setMov_name(mov_name);
		movementVO.setMov_info(mov_info);
		movementVO.setMov_img(mov_img);
		movementVO.setMov_level(mov_level);
		movementVO.setMov_time_length(mov_time_length);
		movementVO.setMov_video(mov_video);
		dao.update(movementVO);
		return movementVO;
	}
	
	// �R��
	public void deleteMovement(String mov_id) {
		dao.delete(mov_id);
	}
	// �d��
	public MovementVO getOneMovement(String mov_id) {
		return dao.findByPrimaryKey(mov_id);
	}
	// �C��
	public List<MovementVO> getAll() {
		return dao.getAll();
	}
	// ���ݳ�@���O�����Ҧ��ʧ@�C��
	public List<MovementVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
}
