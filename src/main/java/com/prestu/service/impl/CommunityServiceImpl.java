package com.prestu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.prestu.mapper.CommunityMapper;
import com.prestu.pojo.Community;
import com.prestu.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @author 范成恒
 */
@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityMapper communityMapper;
    @Override
    public List<Community> findAll() {

        List<Community> communities = communityMapper.selectAll();
        return communities;
    }

    @Override
    public Page<Community> search(Map searchMap) {
        //通用Mapper多条件搜索，标准写法
        Example example = new Example(Community.class);//指定查询的表tb_community
        //1.初始化分页条件
        int pageNum = 1;
        int pageSize = 2;
        String sub = "00:00:00";
        String pre = "23:59:59";
        if (searchMap != null) {
            Example.Criteria criteria = example.createCriteria();//创建查询条件
            //时间区间
            String start = (String) searchMap.get("startTime");
            String end = (String) searchMap.get("endTime");
            String name = (String) searchMap.get("name");
            Integer num = (Integer) searchMap.get("pageNum");
            Integer size = (Integer) searchMap.get("pageSize");
            if (!"".equals(start)) {
                start =start.substring(0,11)+sub;
            }
            if (!"".equals(end)) {
                end =end.substring(0,11)+pre;
            }
            if (StringUtil.isNotEmpty(start)) {
                criteria.andGreaterThanOrEqualTo("createTime", start);
            }
            if (StringUtil.isNotEmpty(end)) {
                criteria.andLessThanOrEqualTo("createTime", end);
            }
            //名称模糊搜索
            if (StringUtil.isNotEmpty(name)) {
                criteria.andLike("name", "%" + name + "%");
            }
            if (num != null) {
                pageNum = num;
            }
            if (size != null) {
                pageSize = size;
            }
        }
        //使用PageHelper插件分页
        // pageNum:当前页码
        //pageSzie：当前页数量
        PageHelper.startPage(pageNum, pageSize);
        Page<Community> communities = (Page<Community>) communityMapper.selectByExample(example);
        return communities;
    }
    @Override
    public Boolean add(Community community) {
        int insert = communityMapper.insert(community);
        return insert > 0;
    }

    @Override
    public Community findById(Integer id) {
        return communityMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean update(Community community) {
        int row = communityMapper.updateByPrimaryKeySelective(community);
        return row > 0;
    }

    @Override
    public Boolean updateStatus(String status, Integer id) {
        Community community = new Community();
        community.setId(id);
        int row = communityMapper.updateByPrimaryKeySelective(community);
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            communityMapper.deleteByPrimaryKey(id);
        }
        return true;
    }

}
