package com.sist.service;

import java.util.List;
import java.util.Map;

import com.sist.vo.GymJjimVO;
import com.sist.vo.GymVO;
import com.sist.vo.NoticeVO;

public interface GymService {
	public List<GymVO> gymFindData(Map map);
	public int gymFindCount(Map map);
	public GymVO gymDetailData(int no);
	public List<GymVO> gymListData(Map map);
	public int gymListCount();
	public GymVO gymListDetailData(int no);
	public List<NoticeVO> noticeTop7();
	public List<GymVO> gymTop7();
	public List<GymVO> gymHome12();
	public int gymJjimCount(Map map);
	public void gymJjimInsertData(Map map);
	public void gymJjimDeleteData(Map map);
	public List<GymJjimVO> gymJjimListData(String userId);

}
