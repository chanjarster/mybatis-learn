package me.chanjar.mybatislearn.localcache.service;

/**
 * Created by qianjia on 2017/1/24.
 */
public interface TestSequenceService {

  Long[] getNextValTwice();

  Long[] getNextValClearCacheTwice();

}
