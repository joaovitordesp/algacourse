package br.com.alga.api.infrastructure.service.storage;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import br.com.alga.api.core.storage.StorageProperties;
import br.com.alga.api.domain.exception.StorageException;
import br.com.alga.api.domain.service.FotoStorageService;
import lombok.var;

@Service
public class S3FotoStorageService implements FotoStorageService{
	
	@Autowired
	private AmazonS3 amazonS3;
	
	@Autowired
	private StorageProperties storageProperties;

	@Override
	public void armazenar(NovaFoto novaFoto) {
		try {
		String caminhoArquivo = getCaminhoArquivo(novaFoto.getNomeArquivo());
		
		var objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(novaFoto.getContentType());
		
		var putObjectRequest = new PutObjectRequest(
				storageProperties.getS3().getBucket(),
				caminhoArquivo, 
				novaFoto.getInputStream(),
				objectMetadata)
				.withCannedAcl(CannedAccessControlList.PublicRead);
		
		amazonS3.putObject(putObjectRequest);
		}catch(Exception e) {
			throw new StorageException("Não foi possível enviar arquivo para Amazon s3.",e);
		}
	}
	
	private String getCaminhoArquivo(String nomeArquivo) {
		return String.format("%s/%s", storageProperties.getS3().getDiretorioFoto(), nomeArquivo);
	}

	@Override
	public InputStream recuperar(String nomeArquivo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remover(String nomeArquivo) {
		// TODO Auto-generated method stub
		
	}
	
}
