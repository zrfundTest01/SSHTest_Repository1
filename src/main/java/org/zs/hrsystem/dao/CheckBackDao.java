package org.zs.hrsystem.dao;

import org.zs.hrsystem.model.CheckBackEntity;

public interface CheckBackDao {
	/**
	 * 持久化指定的CheckBack实例
	 * @param checkBack 需要被持久化的CheckBack实例
	 * @return CheckBack实例被持久化后的标识属性值
	 */
	Integer save(CheckBackEntity checkBack);

}
