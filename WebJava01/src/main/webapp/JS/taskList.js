
function confirmDeletion() {
	return confirm("本当に削除しますか？");
}
function confirmOK() {
	return confirm("タスクを完了しますか？");
}

const detailsElements = document.querySelectorAll('details');

const buttons = document.querySelectorAll("#statusBox button");

document.addEventListener('click', function(event) {
	detailsElements.forEach(details => {
		if (!details.contains(event.target)) {
			details.removeAttribute('open');
		}
	});
	
});

document.addEventListener('DOMContentLoaded' , ()=>{
	const savedElement = localStorage.getItem('clickedElement');
	
	if(savedElement){
		const elementDate = JSON.parse(savedElement);
		
		if(elementDate.id){
			const element = document.getElementById(elementDate.id);
			
			if(element){
				element.classList.add('active');
				console.log('クラスが付与されたはずです');
			}
		}
		console.log(elementDate);
	}else{
		console.log("データが存在しません");
		document.getElementById('allButton').classList.add('active');
	}
})

buttons.forEach(button =>{
	button.addEventListener('click' , (event)=>{
		const target = event.target;
		
		const elementDate = {
			tagName: target.tagName,
			id:target.id || null,
			classList: [...target.classList] || null
		};
		
		const jsonElementDate = JSON.stringify(elementDate, null, 2);
		
		console.log(jsonElementDate);
		
		localStorage.setItem('clickedElement', jsonElementDate);
	})
})

document.querySelector('.logout').addEventListener('click', function(){
	localStorage.clear();
})

//document.addEventListener('DOMContentLoaded',() =>{
//	const statusBox = document.getElementById("statusBox");
//	
//	const activeButtons = JSON.parse(localStorage.getItem('activeButtons') || '[]');
//	
//	activeButtons.forEach(buttonId =>{
//		const button = document.getElementById(buttonId);
//		if(button){
//			button.classList.add('active');
//		}
//	});
//	
//	statusBox.addEventListener('click' , (event)=>{
//		if(event.target && event.target.tagName === 'BUTTON'){
//			const button = event.target;
//			button.classList.add('active');
//			
//			const activeButtons = JSON.parse(localStorage.getItem('activeButtons') || '[]');
//			if(!activeButtons.includes(button.id)){
//				activeButtons.push(button.id);
//				localStorage.setItem('activeButtons',JSON.stringify(activeButtons));
//			}
//		}
//	})
//	
//})



