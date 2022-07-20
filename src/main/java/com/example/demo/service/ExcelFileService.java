package com.example.demo.service;

import com.example.demo.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

public abstract class ExcelFileService {

    public abstract ByteArrayInputStream export(List<Board> boards);
}


