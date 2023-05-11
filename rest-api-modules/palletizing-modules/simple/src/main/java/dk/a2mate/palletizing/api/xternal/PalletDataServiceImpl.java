package dk.a2mate.palletizing.api.xternal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.a2mate.palletizing.api.model.Item;
import dk.a2mate.palletizing.api.model.Pallet;
import dk.a2mate.palletizing.api.repository.ItemDao;
import dk.a2mate.palletizing.api.repository.ItemRepository;
import dk.a2mate.palletizing.api.repository.PalletDao;
import dk.a2mate.palletizing.api.repository.PalletRepository;
import dk.a2mate.palletizing.api.sevice.PalletDataService;
import dk.a2mate.palletizing.api.sevice.PalletizingServiceImpl;

@Service
public class PalletDataServiceImpl implements PalletDataService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PalletDataServiceImpl.class);

	@Autowired
	private PalletRepository palletRepository;

	@Override
	public Pallet findById(String palletId) {
		LOGGER.debug("findById {}", palletId);
		PalletDao dao = palletRepository.findById(palletId).get();
		return toPallet(dao);
	}

	@Override
	public Pallet savePallet(Pallet pallet) {
		PalletDao d = new PalletDao();
		d.setId(pallet.getId());
		d.setHeight(pallet.getHeight());
		d.setLength(pallet.getLength());
		d.setName(pallet.getName());
		d.setWeight(pallet.getWeight());
		d.setWidth(pallet.getWidth());
		d.setMaxHeight(pallet.getMaxHeight());
		d.setMaxWeight(pallet.getWeight());
		palletRepository.save(d);
		return pallet;
	}

	@Override
	public List<Pallet> fetchPalletList() {
		LOGGER.debug("toPallets {}");
		List<PalletDao> pallets = new ArrayList<PalletDao>();
        Iterable<PalletDao> iter = palletRepository.findAll();
        iter.forEach((p) -> {
    		LOGGER.info("fetchPalletList() --");

            pallets.add(p);
        });
		return toPallets(pallets);
	}

	@Override
	public Pallet updatePallet(Pallet pallet, String palletId) {
		PalletDao d = palletRepository.findById(palletId).get();
		d.setHeight(pallet.getHeight());
		d.setLength(pallet.getLength());
		d.setName(pallet.getName());
		d.setWeight(pallet.getWeight());
		d.setWidth(pallet.getWidth());

		return pallet;
	}

	@Override
	public void deletePalletById(String palletId) {
		palletRepository.deleteById(palletId);
	}

	private List<Pallet> toPallets(List<PalletDao> pallets) {
		LOGGER.info("toPallets {}", pallets.size());
		List<Pallet> rtn = pallets.stream().map(p -> toPallet(p)).collect(Collectors.toList());
		return rtn;
	}

	private Pallet toPallet(PalletDao id) {
		LOGGER.info("toPallet {}", id.getId());
		Pallet d = new Pallet();
		d.setId(id.getId());
		d.setHeight(id.getHeight());
		d.setLength(id.getLength());
		d.setName(id.getName());
		d.setWeight(id.getWeight());
		d.setWidth(id.getWidth());
		d.setMaxLoad(id.getMaxWeight());
		d.setMaxHeight(id.getMaxHeight());
		return d;
	}

}
