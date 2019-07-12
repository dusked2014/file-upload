package com.neo.service;

import com.neo.domain.NeoFile;

public interface NeoFileService {

    public void saveNeoFile(NeoFile neoFile);

    public NeoFile queryNeoFile(NeoFile neoFile);
}
