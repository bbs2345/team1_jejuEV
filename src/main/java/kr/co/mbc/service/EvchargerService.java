package kr.co.mbc.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Comparator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import kr.co.mbc.dto.Criteria;
import kr.co.mbc.entity.EvChagerEntity;
import kr.co.mbc.entity.EvChargingStationEntity;
import kr.co.mbc.repository.EvChargerRepository;
import kr.co.mbc.repository.EvStationRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EvchargerService {

    private final EvChargerRepository evChargerRepository;
    
    private final EvStationRepository evStationRepository;
    
    private final String apiUrl = "http://apis.data.go.kr/B552584/EvCharger/getChargerInfo";
    private final String apiKey = "zdcb/dCnKTh9GswIuVYaVktIh68IRBPbWnnyqE6jqG5npU0ztdVPnHwocG98iL0HPqqufx2nKNqdbowVwrBu0Q==";
    
    // API로부터 데이터를 가져오는 메서드
    public String fetchData(String apiUrl, String apiKey) throws Exception {
        String fullUrl = apiUrl + "?serviceKey=" + apiKey + "&zcode=50&numOfRows=9999"; // 요청 URL 생성
        HttpURLConnection conn = (HttpURLConnection) new URL(fullUrl).openConnection(); // URL 연결
        conn.setRequestMethod("GET"); // GET 요청 설정
        conn.setRequestProperty("Authorization", "Bearer " + apiKey); // API 키를 요청 헤더에 추가

        int responseCode = conn.getResponseCode(); // 응답 코드 확인
        if (responseCode == HttpURLConnection.HTTP_OK) {
            return readResponse(conn); // 응답이 성공적이면 응답 내용 읽기
        } else {
            throw new RuntimeException("Failed to fetch data: HTTP " + responseCode); // 실패 시 예외 발생
        }
    }
    
    // HTTP 응답을 읽어 문자열로 반환하는 메서드
    public String readResponse(HttpURLConnection conn) throws Exception {
        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line); // 응답 내용을 StringBuilder에 추가
            }
        }
        return response.toString();
    }

    // EvChargingStation 저장 메서드
    @Transactional
    public void saveDataFromXml(String xmlData) throws Exception {
        // XML 데이터를 파싱하여 Document 객체로 변환
        Document doc = parseXml(xmlData);
        NodeList items = doc.getElementsByTagName("item");  // XML에서 "item" 태그를 가진 노드 목록 가져오기
        
        // 각 "item" 노드에 대해 EvChagerEntity 객체 생성 및 저장
        for (int i = 0; i < items.getLength(); i++) {
            Element item = (Element) items.item(i);
            
            EvChargingStationEntity chargingStationEntity = createStationEntityFromxml(item);
            
            // 충전소 저장
            saveChargingStation(chargingStationEntity);
        }
    }

    // 충전소 저장 메소드
    private void saveChargingStation(EvChargingStationEntity chargingStationEntity) {
        // 중복 확인
        boolean exists = evStationRepository.existsByStatIdAndAddrAndLatAndLng(
                chargingStationEntity.getStatId(),
                chargingStationEntity.getAddr(),
                chargingStationEntity.getLat(),
                chargingStationEntity.getLng()
        );
        
        // 중복된 충전소 정보가 없다면 저장
        if (!exists) {
            evStationRepository.save(chargingStationEntity);  // 충전소 저장
        }
    }

    // EvChagerEntity 저장 메소드
    @Transactional
    public void saveChagerEntity(String xmlData) throws Exception {
        Document doc = parseXml(xmlData);
        NodeList items = doc.getElementsByTagName("item");

        for (int i = 0; i < items.getLength(); i++) {
            Element item = (Element) items.item(i);
            EvChagerEntity entity = createEntityFromXml(item);

            // 중복된 충전소 엔티티 리스트 가져오기
            List<EvChargingStationEntity> stations = evStationRepository.findByStatNm(entity.getStatNm());

            if (stations.isEmpty()) {
                System.err.println("No matching station found for statNm: " + entity.getStatNm());
                continue; // 매칭되는 충전소가 없으면 다음으로 넘어감
            }

            EvChargingStationEntity chosenStation = stations.get(0);

            // 선택된 충전소를 설정하고 저장
            entity.setStation(chosenStation);
            entity.setStatId(stations.get(0).getStatId());
            
            evChargerRepository.save(entity);
        }
    }
    
    // 두 테이블에 동시에 넣으려고 하니 외래키 문제로 작동이 안되서 두개로 분리
//    // DB에 API 데이터 저장하는 메서드
//    @Transactional
//    public void saveDataFromXml(String xmlData) throws Exception {
//    	
//    	// XML 데이터를 파싱하여 Document 객체로 변환
//        Document doc = parseXml(xmlData);
//        NodeList items = doc.getElementsByTagName("item");  // XML에서 "item" 태그를 가진 노드 목록 가져오기
//        
//        // 각 "item" 노드에 대해 EvchagerEntity 객체 생성 및 저장
//        for (int i = 0; i < items.getLength(); i++) {
//            Element item = (Element) items.item(i);
//            
//            EvChargingStationEntity chargingStationEntity = createStationEntityFromxml(item);
//            
//            // 중복 확인
//            boolean exists = evStationRepository.existsByStatIdAndAddrAndLatAndLng(
//            		chargingStationEntity.getStatId(),
//            		chargingStationEntity.getAddr(),
//            		chargingStationEntity.getLat(),
//            		chargingStationEntity.getLng()
//            		);
//            
//            // 중복된 충전소 정보가 없다면 저장
//            if (!exists) {
//            	evStationRepository.save(chargingStationEntity);
//            }
//            
//            // EvChagerEntity는 충전소 저장 여부와 관계없이 저장
//            EvChagerEntity entity = createEntityFromXml(item);
//            entity.setStation(chargingStationEntity);
//            evChargerRepository.save(entity);
//
//        }
//    }
    
    // XML 문자열을 Document 객체로 변환하는 메서드
    public Document parseXml(String xmlData) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xmlData));
        return builder.parse(is); // 파싱된 Document 객체 반환
    }
    
    // XML 요소에서 특정 태그의 값을 가져오는 메서드
    public String getTagValue(Element item, String tagName) {
        Node node = item.getElementsByTagName(tagName).item(0);
        return (node != null) ? node.getTextContent() : null; // 태그가 존재하면 값 반환, 없으면 null 반환
    }
    
    // XML 요소로부터 EvChagerEntity 객체를 생성하는 메서드
    public EvChagerEntity createEntityFromXml(Element item) {
        return EvChagerEntity.builder().chgerId(getTagValue(item, "chgerId"))
        .statNm(getTagValue(item, "statNm"))
        .chgerType(getTagValue(item, "chgerType"))
        .addrDetail(getTagValue(item, "addrDetail"))
        .location(getTagValue(item, "location"))
        .bnm(getTagValue(item, "bnm"))
        .stat(getTagValue(item, "stat"))
        .statUpdDt(getTagValue(item, "statUpdDt"))
        .lastTsdt(getTagValue(item, "lastTsdt"))
        .lastTedt(getTagValue(item, "lastTedt"))
        .nowTsdt(getTagValue(item, "nowTsdt"))
        .powerType(getTagValue(item, "powerType"))
        .output(getTagValue(item, "output"))
        .method(getTagValue(item, "method"))
        .zscode(getTagValue(item, "zscode"))
        .kind(getTagValue(item, "kind"))
        .kindDetail(getTagValue(item, "kindDetail"))
        .limitYn(getTagValue(item, "limitYn"))
        .limitDetail(getTagValue(item, "limitDetail"))
        .delYn(getTagValue(item, "delYn"))
        .delDetail(getTagValue(item, "delDetail"))
        .trafficYn(getTagValue(item, "trafficYn"))
        .build();
    }
    
    // XML 요소로부터 EvChargingStationEntity 객체를 생성하는 메서드
    public EvChargingStationEntity createStationEntityFromxml(Element item) {
    	return EvChargingStationEntity.builder()
    			.statId(getTagValue(item, "statId"))
    			.statNm(getTagValue(item, "statNm"))
    			.addr(getTagValue(item, "addr"))
    			.lat(getTagValue(item, "lat"))
    			.lng(getTagValue(item, "lng"))
    			.useTime(getTagValue(item, "useTime"))
    			.busiId(getTagValue(item, "busiId"))
    			.busiNm(getTagValue(item, "busiNm"))
    			.busiCall(getTagValue(item, "busiCall"))
    			.zcode(getTagValue(item, "zcode"))
    			.parkingFree(getTagValue(item, "parkingFree"))
    			.note(getTagValue(item, "note"))
    			.build();
    }


	public List<EvChargingStationEntity> findAll(Criteria criteria) {
		
		return evStationRepository.findAll(criteria);
	}

	public Long getTotalCount(Criteria criteria) {
		
		return evStationRepository.getTotalCount(criteria);
	}

	public List<EvChagerEntity> findAll() {
		
		return evChargerRepository.findAll();
	}

    
}