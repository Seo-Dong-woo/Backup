package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;

public interface GymMapper {
	@Select("SELECT no, poster, title, num "
			+ "FROM (SELECT no, poster, title, rownum as num "
			+ "FROM (SELECT no, poster, title "
			+ "FROM gym "
			+ "WHERE address LIKE '%'||#{address}||'%' "
			+ "ORDER BY no ASC)) "
			+ "WHERE num  BETWEEN #{start} AND #{end}")
	public List<GymVO> gymFindData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM gym "
			+"WHERE address LIKE '%'||#{address}||'%'")
	public int gymFindCount(Map map);
	
	@Select("SELECT no, title, poster, address, time, phone "
			+ "FROM gym "
			+ "WHERE no=#{no}")
	public GymVO gymDetailData(int no);
	
	@Update("UPDATE gym SET "
			+ "hit=hit+1 "
			+ "WHERE no=#{no}")
    public void gymHitIncrement(int no);
	
	@Select("SELECT no, poster, title, num "
			+"FROM (SELECT no, poster, title, rownum as num "
			+"FROM (SELECT no, poster, title "
			+"FROM gym "
			+"ORDER BY no ASC)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	public List<GymVO> gymListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM gym")
	public int gymListCount();
	
	@Select("SELECT no, title , rownum "
			+"FROM (SELECT no, title "
			+"FROM gym ORDER BY hit DESC) "
			+"WHERE rownum<=7")
	public List<GymVO> gymTop7();
	  
	@Select("SELECT no, title, poster, rownum "
			+ "FROM (SELECT no, title, poster "
			+ "FROM gym ORDER BY hit DESC) "
			+ "WHERE rownum<=12")
	public List<GymVO> gymHome12();
	
	//////////////////////////////////////////////////////////////////
	// 찜 있는지 확인
	@Select("SELECT COUNT(*) FROM gymjjim WHERE userId=#{userId} AND no=#{no}")
	public int gymJjimCount(Map map);
		
	// 찜 저장
	@Insert("INSERT INTO gymjjim VALUES(gj_gjno_seq.nextval,#{userId},#{no})")
	public void gymJjimInsertData(Map map);
		
	// 찜 삭제
	@Delete("DELETE FROM gymjjim WHERE userId=#{userId} AND no=#{no}")
	public void gymJjimDeleteData(Map map);
	
	// 찜 목록 => 마이페이지 찜 관리
	@Results({
		@Result(column = "title", property = "gvo.title"),
		@Result(column = "poster", property = "gvo.poster"),
		@Result(column = "phone", property = "gvo.phone")
	})
	@Select("SELECT gjno, gj.no, userId, title, poster, phone, "
			+ "FROM gymjjim gj, gym g "
			+ "WHERE gj.no=g.no "
			+ "ORDER BY gjno DESC")
	public List<GymJjimVO> gymJjimListData(String userId);
	
}
