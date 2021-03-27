package com.example.study.ifs;

import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import org.springframework.web.bind.annotation.PostMapping;

public interface CrudInterface<Req,Res> {


    Header<Res> create(Header<Req> request);//todo request object 추가

    Header<Res> read(Long id);

    Header<Res> update(Header<Req> request);

    Header delete(Long id);

}
