package br.com.alga.api.core.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import br.com.alga.api.core.storage.StorageProperties.TipoStorage;
import br.com.alga.api.domain.service.FotoStorageService;
import br.com.alga.api.infrastructure.service.storage.LocalFotoStorageService;
import br.com.alga.api.infrastructure.service.storage.S3FotoStorageService;
import lombok.var;

@Configuration
public class StorageConfig {

	@Autowired
	private StorageProperties storageProperties;

	@Bean
	public AmazonS3 amazonS3() {
		var credentials = new BasicAWSCredentials(storageProperties.getS3().getIdAccessKey(),
				storageProperties.getS3().getSecretAccessKey());
		
		return AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(storageProperties.getS3().getRegion())
				.build();
	}
	
	public FotoStorageService fotoStorageService() {
		if(TipoStorage.S3.equals(storageProperties.getTipo())) {
			return new S3FotoStorageService();
		}else {
			return new LocalFotoStorageService();
		}
	}

}
