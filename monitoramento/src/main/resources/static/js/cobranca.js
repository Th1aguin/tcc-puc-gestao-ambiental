$('#modalDelete').on('shown.bs.modal', function (e) {
  
	button = $(e.relatedTarget)
	
	codigo = button.data('codigo')
	desc = button.data('descricao')
	
	modal = $(this)
	form = modal.find('form')
	action = form.attr('action')
	form.attr('action',action+codigo)
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o título ' +desc +' ? ')
})

$(function(){
	
	 $('[rel="tooltip"]').tooltip();
	 $('.currency').maskMoney({thousands:'.', decimal:','});
	 
	 $('.receber').on('click',function(e){
		 e.preventDefault();
		 
		 link = $(this);
		 
		 var response = $.ajax({
			 url: link.attr('href'),
			 type: 'PUT'
		 })
		 
		 response.done(function(){
			 codigo = link.data('codigo')
			 $('[data-role="'+codigo+'"]').html('<span class="label  label-success">Recebido</span>');
			 link.hide();
		 })
		 
	 })
})