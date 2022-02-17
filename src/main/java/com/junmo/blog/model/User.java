package com.junmo.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
//ORM -> Object -> Table로 만들어줌

//@DynamicInsert  //insert 시에 null인 필드를 제외시켜준다
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //User 클래스의 필드를 읽어서 MySQL에 자동으로 테이블 생성

public class User {
	
	@Id //PK
	@GeneratedValue(strategy =GenerationType.IDENTITY) //프로젝트에서 연결된 DB(오라클,MYSQL)의 넘버링전략을 따라감.
	//자동입력
	private int id; //시퀀스 , auto_increment
	
	@Column(nullable=false,length=30)
	private String username; //아이디
	
	@Column(nullable=false,length=100) //123456 -> 해쉬 (비밀번호 암호화)
	private String password;
	
	@Column(nullable=false,length=50)
	private String email;
	
	//@ColumnDefault("user")
	//DB는 RoleType가 없음
	@Enumerated(EnumType.STRING)
	private RoleType role;  //Enum을 쓰는게 좋다 . 범위를 지정할수있음  String으로 지정시 오타발생하여 잘못된 값입력가능성 // admin,user,manager  권한
	
	@CreationTimestamp //시간 자동입력
	private Timestamp createDate;
	
}
