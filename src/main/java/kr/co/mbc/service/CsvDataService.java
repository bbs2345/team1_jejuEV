package kr.co.mbc.service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import kr.co.mbc.entity.CsvDataEntity;
import kr.co.mbc.repository.CsvDbRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CsvDataService {

    private final CsvDbRepository csvDbRepository;

    /**
     * CSV 파일을 UTF-8로 변환한 뒤 데이터베이스에 저장
     *
     * @param file 업로드된 MultipartFile 객체
     * @throws IOException 파일 처리 중 발생하는 예외
     */
    public void csvToDatabase(MultipartFile file) throws IOException {
        // 1. 업로드된 파일을 UTF-8로 변환하여 임시 파일로 저장
        File utf8File = convertToUtf8(file);

        // 2. 변환된 파일을 읽고 데이터베이스에 저장
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream(utf8File), StandardCharsets.UTF_8))) {
            List<String[]> records;

            try {
                records = csvReader.readAll(); // CSV 파일의 모든 데이터를 읽음
            } catch (CsvException e) {
                System.err.println("CSV 파일 읽기 오류: " + e.getMessage());
                return; // 예외 발생 시 처리를 중단
            }

            // 3. 읽은 데이터를 처리
            for (String[] record : records) {
                if (isValidRecord(record)) { // 유효한 레코드인지 확인
                    processRecord(record); // 레코드 처리
                }
            }
        } finally {
            // 임시 파일 삭제
            utf8File.delete();
        }
    }

    /**
     * 업로드된 파일을 UTF-8로 변환하여 임시 파일로 저장
     *
     * @param file 업로드된 MultipartFile 객체
     * @return 변환된 UTF-8 파일
     * @throws IOException 파일 처리 중 발생하는 예외
     */
    private File convertToUtf8(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "EUC-KR")); // ANSI 파일 읽기

        // UTF-8로 변환된 파일을 저장할 임시 파일 생성
        File tempFile = File.createTempFile("uploaded-", ".csv");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile), StandardCharsets.UTF_8));

        // 라인 단위로 읽어서 UTF-8로 변환하며 저장
        String line;
        while ((line = reader.readLine()) != null) {
            writer.write(line);
            writer.newLine();
        }

        // 리소스 닫기
        writer.close();
        reader.close();

        return tempFile;
    }

    // 유효한 레코드인지 확인하는 메서드
    private boolean isValidRecord(String[] record) {
        return record.length == 11; // CSV 필드 수 확인 (필드 수는 CSV 구조에 맞게 조정)
    }

    // 레코드를 처리하는 메서드
    private void processRecord(String[] record) {
        Optional<CsvDataEntity> opt = csvDbRepository.findById(10000000000L);

        if (opt.isEmpty()) {
            CsvDataEntity csvDataEntity = createCsvDataEntity(record);

            csvDbRepository.save(csvDataEntity); // DB에 저장
        }
    }

    // CsvDataEntity를 생성하는 메서드
    private CsvDataEntity createCsvDataEntity(String[] record) {
        return CsvDataEntity.builder()
                .bnm(record[0])
                .statId(record[1])
                .statNm(record[2])
                .chgerId(record[3])
                .chgerNm(record[4])
                .chgerType(record[5])
                .chgerStdt(record[6])
                .chgerCount(record[7])
                .chgerTime(record[8])
                .chgerAm(record[9])
                .chgerCost(record[10])
                .build();
    }
}
