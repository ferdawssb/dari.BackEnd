package dari.BackEnd.services;
import java.util.List;
import dari.BackEnd.entites.Logement;

public interface LogementService {
	
	
	public List<Logement> getAllLog();

	public Logement findLogById(int id);

	public Logement createLog(Logement l);

	public Logement updateLog(Logement l);

	public void deleteLog(int id);
	

}
