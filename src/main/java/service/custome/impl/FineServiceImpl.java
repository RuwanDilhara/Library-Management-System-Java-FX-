package service.custome.impl;

import dto.Fine;
import entity.FineEntity;
import org.modelmapper.ModelMapper;
import repository.custome.FineDao;
import repository.custome.impl.FineDaoImpl;
import service.custome.FineService;

import java.util.List;

public class FineServiceImpl implements FineService {
    FineDao fineDao = new FineDaoImpl();

    @Override
    public List<Fine> getAll() {
        return fineDao.getAll().stream().map(fineEntity ->
            new ModelMapper().map(fineEntity, Fine.class)
        ).toList();
    }

    @Override
    public Fine fineById(String id) {
        return null;
    }

    @Override
    public boolean save(Fine fine) {
        return fineDao.save(new ModelMapper()
                .map(fine,FineEntity.class));
    }

    @Override
    public List<Fine> fineByMemberId(String id) {
        return List.of();
    }

    @Override
    public List<Fine> fineByBorrowRecordId(String id) {
        return List.of();
    }
}
