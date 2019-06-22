$('#modalDelete').on('shown.bs.modal', function (e) {
  
	button = $(e.relatedTarget)
	
	codigo = button.data('id')
	desc = button.data('descricao')
	loc = button.data('loc')
	
	modal = $(this)
	form = modal.find('form')
	form.attr('action',loc+codigo)
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir ' +desc +' ? ')
})

$('#modalPanico').on('shown.bs.modal', function (e) {
  
	button = $(e.relatedTarget)
	
	codigo = button.data('id')
	desc = button.data('descricao')
	loc = button.data('loc')
	
	modal = $(this)
	form = modal.find('form')
	form.attr('action',loc+codigo)
	
	modal.find('.modal-body h4').html('Tem certeza que deseja disparar o procedimento de evacuação para a barragem  ' +desc +'?*')
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