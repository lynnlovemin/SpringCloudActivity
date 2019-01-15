package com.lynn.blog.common.tree.listener;

/**
 * 树形结构监听器
 * @author 李熠
 *
 */
public interface TreeListener {

	/**
	 * 每次循环返回索引和当前循环的对象
	 * @param instance
	 * @param position
	 */
	public void each(Object instance, int position);
}
