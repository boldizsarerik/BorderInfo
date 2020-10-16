package com.application.borderinfo.service;

import com.application.borderinfo.entity.Border;
import com.application.borderinfo.repository.BorderRepository;
import com.application.borderinfo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BorderDataService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BorderRepository borderRepository;

    public ArrayList<String> checkBorders() {
        ArrayList<String> borders = new ArrayList<String>();
        borders.add("Beregsurány -> Asztély");
        borders.add("Asztély -> Beregsurány");
        borders.add("Barabás -> Kaszony");
        borders.add("Kaszony -> Barabás");
        borders.add("Tiszabecs -> Tiszaújlak");
        borders.add("Tiszaújlak -> Tiszabecs");
        borders.add("Záhony -> Csap");
        borders.add("Csap -> Záhony");
        borders.add("Lónya -> Harangláb");
        borders.add("Harangláb -> Lónya");

        return borders;
    }

    public List<Border> indexBorders() {
        List<Border> borders = new ArrayList<Border>();
        if(!borderRepository.findFirstByNameOrderByDateDesc("Beregsurány -> Asztély").equals(null))
        {
            borders.add(borderRepository.findFirstByNameOrderByDateDesc("Beregsurány -> Asztély"));
        }

        borders.add(borderRepository.findFirstByNameOrderByDateDesc("Asztély -> Beregsurány"));
        borders.add(borderRepository.findFirstByNameOrderByDateDesc("Barabás -> Kaszony"));
        borders.add(borderRepository.findFirstByNameOrderByDateDesc("Kaszony -> Barabás"));
        borders.add(borderRepository.findFirstByNameOrderByDateDesc("Tiszabecs -> Tiszaújlak"));
        borders.add(borderRepository.findFirstByNameOrderByDateDesc("Tiszaújlak -> Tiszabecs"));
        borders.add(borderRepository.findFirstByNameOrderByDateDesc("Lónya -> Harangláb"));
        borders.add(borderRepository.findFirstByNameOrderByDateDesc("Harangláb -> Lónya"));
        borders.add(borderRepository.findFirstByNameOrderByDateDesc("Záhony -> Csap"));
        borders.add(borderRepository.findFirstByNameOrderByDateDesc("Csap -> Záhony"));

        return borders;
    }
    public List<Border> indexUaBorders() {
        List<Border> borders = new ArrayList<Border>();
        if(!borderRepository.findFirstByNameOrderByDateDesc("Beregsurány -> Asztély").equals(null))
        {
            borders.add(borderRepository.findFirstByNameOrderByDateDesc("Beregsurány -> Asztély"));
        }

        borders.add(borderRepository.findFirstByNameOrderByDateDesc("Barabás -> Kaszony"));
        borders.add(borderRepository.findFirstByNameOrderByDateDesc("Tiszabecs -> Tiszaújlak"));
        borders.add(borderRepository.findFirstByNameOrderByDateDesc("Lónya -> Harangláb"));
        borders.add(borderRepository.findFirstByNameOrderByDateDesc("Záhony -> Csap"));

        return borders;
    }
    public List<Border> indexHuBorders() {
        List<Border> borders = new ArrayList<Border>();

        borders.add(borderRepository.findFirstByNameOrderByDateDesc("Asztély -> Beregsurány"));
        borders.add(borderRepository.findFirstByNameOrderByDateDesc("Kaszony -> Barabás"));
        borders.add(borderRepository.findFirstByNameOrderByDateDesc("Tiszaújlak -> Tiszabecs"));
        borders.add(borderRepository.findFirstByNameOrderByDateDesc("Harangláb -> Lónya"));
        borders.add(borderRepository.findFirstByNameOrderByDateDesc("Csap -> Záhony"));

        return borders;
    }
}
