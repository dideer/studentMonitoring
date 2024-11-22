package javawithdatabase.crudspringboot.service;

import javawithdatabase.crudspringboot.model.gateAttendance;

import java.util.List;

public interface GateAttendanceService {
    List<gateAttendance> getTodayAttendanceByLevel(Long levelId);
    List<gateAttendance> getTodayAttendanceByLevels(Long levelId);
    List<gateAttendance> weeklyReport(Long levelId);
    List<gateAttendance> monthlyReport(Long levelId, int month);



}
