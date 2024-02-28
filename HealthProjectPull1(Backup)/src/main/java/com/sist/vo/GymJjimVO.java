package com.sist.vo;

import lombok.Data;

/*
 *  CREATE TABLE gymjjim(
		   gjno NUMBER,
		   userId VARCHAR2(20),
		   no NUMBER,
		   CONSTRAINT gj_gjno_pk PRIMARY KEY(gjno),
		   CONSTRAINT gj_userId_fk FOREIGN KEY(userId)
		   REFERENCES hhfinalMember(userId),
		   CONSTRAINT gj_no_fk FOREIGN KEY(no)
		   REFERENCES gym(no)
	);
 */
@Data
public class GymJjimVO {
	private int gjno, no;
	private String userId;
	private GymVO gvo=new GymVO();
}
