package com.dskimina.services;

import com.dskimina.data.Log;
import com.dskimina.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public void createLog(String actionName, String user, long actionDuration){
        Log log = new Log();
        log.setActionDate(new Date());
        log.setActionTime(actionDuration);
        log.setActionType(actionName);
        log.setUser(user);
        logRepository.save(log);
    }

    public List<Log> getAllLogs(){
        List<Log> logs = new ArrayList<>();
        for(Log log : logRepository.findAll()){
            logs.add(log);
        }
        return logs;
    }
}
