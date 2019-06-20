package br.com.puc.tcc.service.exception;

public class ServiceException extends RuntimeException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1869300553614629710L;

	public ServiceException(String mensagem) {
		super(mensagem);
	}
	
	public ServiceException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}
