package com.db2Hibernate.proj.service;

import com.db2Hibernate.proj.AppProp;
import com.db2Hibernate.proj.dto.EmployeeDTO;
import com.db2Hibernate.proj.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class WriteToFile {
    @Autowired
    AppProp prop;

    public boolean saveEmployeesToFile(EmployeeDTO employee, String line) throws IOException {
        boolean saved = false;
        String dirName = prop.SYSTEM_DIRECTORY;
        String fileName = employee.getDepartmentName() + ".txt";
        File dir = new File(dirName);
        dir.mkdirs();
        File file = new File(dir, fileName);
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);

        try {

                bw.write(line);
            saved = true;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally
        {
            bw.close();
        }


        return saved;
    }

}
