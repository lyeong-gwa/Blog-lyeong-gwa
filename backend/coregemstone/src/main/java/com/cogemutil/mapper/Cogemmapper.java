package com.cogemutil.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface Cogemmapper {
	int countImageUploadTry();

	int countCoreCalcTry(@Param("job") String job, @Param("core_num") int core_num,
			@Param("select_skill") String select_skill, @Param("combi_list_len") int combi_list_len);
}
