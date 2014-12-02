package com.cpp2.dao;

import java.util.List;

import com.cpp2.domain.User;

public interface UserDAO
{

	/**
	 * ��tb_user ����һ����¼
	 * @param user
	 */
	void create(User user);

	/**
	 * ����idɾ��tb_user��һ����¼
	 * @param id
	 */
	void delete(int id);

	/**
	 * modify tb_user
	 * @prama user
	 */
	void update(User user);

	/**
	 * ����id�����û�
	 * @param id
	 * @return
	 */
	User retrieve(int id);

	/**
	 * �����û�������������û�
	 * @param username
	 * @param password
	 * @return
	 */
	User retrieve(String username, String password);

	/**
	 * �����û��������û�
	 * @param username
	 * @return
	 */
	User retrieve(String username);
	
	/**
	 * ��ȡ�����û�
	 * @return
	 */
	List<User> getAll();

	void updatePassword(String newpassword, int id);

	List<User> getAllUserPageDate(int beginIndex, int everyPage);

	int getTotalRecord();

	// �û�����ȡ
	User retrieveFromUsername(String username, String password);

	// ��������ȡ
	User retrieveFromEmail(String email, String password);

	// �ֻ��Ż�ȡ
	User retrieveFromPhone(String phone, String password);
}