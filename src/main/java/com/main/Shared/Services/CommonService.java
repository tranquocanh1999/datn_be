package com.main.Shared.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.Models.Search;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.UUID;

@Service
public class CommonService {
    public PageRequest getPage(Search data) {
        Sort sortable = null;

        if (data.getSort() != null && data.getSort().getValue().toUpperCase().equals("ASC")) {
            sortable = Sort.by(data.getSort().getName()).ascending();
        }
        if (data.getSort() != null && data.getSort().getValue().toUpperCase().equals("DESC")) {
            sortable = Sort.by(data.getSort().getName()).descending();
        }
        if (Objects.isNull(sortable)) {
            return PageRequest.of(data.getPage()-1, data.getPerPage());
        }
        return PageRequest.of(data.getPage()-1, data.getPerPage(), sortable);
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    public String encryptPass(String pass)  {
       return DigestUtils.sha256Hex(pass);
    }

    public UUID convertBytesToUUID(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        long high = byteBuffer.getLong();
        long low = byteBuffer.getLong();
        return new UUID(high, low);
    }
}
