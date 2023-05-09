package dk.a2mate.palletizing.api.xternal;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

import dk.a2mate.palletizing.api.model.Pallet;
import dk.a2mate.palletizing.api.sevice.PalletDataService;

@Service
public class PalletDataServiceImpl implements PalletDataService {

	static HashMap<String, Pallet> palletStore = new HashMap<String, Pallet>();

	public PalletDataServiceImpl() {
		if(palletStore.isEmpty()) {
			palletStore.putIfAbsent("EUR", getEUR());
			palletStore.putIfAbsent("EUR2", getEUR2());
		}
	}
	
 	@Override
	public Pallet findById(String id) {
		return palletStore.get(id);
	}

	@Override
	public Set<Pallet> findAll() {

		Set<Pallet> pallets = new TreeSet<>(new Comparator<Pallet>() {
			@Override
			public int compare(Pallet o1, Pallet o2) {
				// Define comparing logic here
				return o1.getName().compareTo(o2.getName());
			}

		});
		pallets.addAll(palletStore.values());
		return pallets;
	}

	private Pallet getEUR() {
		Pallet p = new Pallet();
		p.setId("EUR");
		p.setHeight(12);
		p.setMaxHeight(1500);
		p.setMaxLoad(4000);
		p.setName("Europa palle");
		p.setWeight(10);
		p.setWidth(800);
		p.setLength(1200);
		return p;
	}

	private Pallet getEUR2() {
		Pallet p = new Pallet();
		p.setId("EUR2");
		p.setHeight(18);
		p.setMaxHeight(1500);
		p.setMaxLoad(4000);
		p.setName("Europa2 palle");
		p.setWeight(10);
		p.setWidth(1200);
		p.setLength(1000);
		return p;
	}

	@Override
	public void createPallet(Pallet pallet) {
		palletStore.putIfAbsent(pallet.getId(), pallet);
		
	}

}
