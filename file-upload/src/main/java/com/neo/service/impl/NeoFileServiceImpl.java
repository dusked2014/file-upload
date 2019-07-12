package com.neo.service.impl;

import com.neo.domain.NeoFile;
import com.neo.mapper.NeoFileMapper;
import com.neo.service.NeoFileService;
import com.neo.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * Created by zhu on 2019-07-10 23:24
 */
@Service
public class NeoFileServiceImpl implements NeoFileService {

    @Autowired
    NeoFileMapper neoFileMapper;

    @Override
    public void saveNeoFile(NeoFile neoFile) {
        Example example = new Example(NeoFile.class);
        example.createCriteria().andEqualTo("fileName", neoFile.getFileName());

        NeoFile neoFile1 = neoFileMapper.selectOneByExample(example);

        if (neoFile1 == null) {
            neoFileMapper.insert(neoFile);
        } else {
            neoFile.setCreateTime(new Date());
            neoFileMapper.updateByExampleSelective(neoFile, example);
        }

    }

    @Override
    public NeoFile queryNeoFile(NeoFile neoFile) {
        Example example = new Example(NeoFile.class);
        if (neoFile != null && StringUtils.isNotBlank(neoFile.getFileName())) {
            example.createCriteria().andEqualTo("fileName", neoFile.getFileName());
        }else if("true".equalsIgnoreCase(neoFile.getFileLinkUrl())){
            example.orderBy("createTime").desc();
            example.createCriteria().andIsNotNull("fileLinkUrl");
        }else {
            example.orderBy("createTime").desc();
        }
        List<NeoFile> neoFile1 = neoFileMapper.selectByExample(example);
        return neoFile1.get(0);

    }
}