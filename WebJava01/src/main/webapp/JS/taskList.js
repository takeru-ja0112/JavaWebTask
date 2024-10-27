
function confirmDeletion() {
	return confirm("本当に削除しますか？");
}
function confirmOK() {
	return confirm("タスクを完了しますか？");
}

const detailsElements = document.querySelectorAll('details');

//抽出用ボタン一覧取得
const buttons = document.querySelectorAll("#statusBox button");

document.addEventListener('click', function(event) {
	detailsElements.forEach(details => {
		if (!details.contains(event.target)) {
			details.removeAttribute('open');
		}
	});
	
});

//最初に読み込まれたタイミングで実行される関数
document.addEventListener('DOMContentLoaded' , ()=>{
	
//	クリックした時の要素をJSON形式で呼び出す
	const savedElement = localStorage.getItem('clickedElement');
	
//	もし要素が存在したら
	if(savedElement){
		
//		JSON形式からjsで使えるオブジェクトに変換
		const elementDate = JSON.parse(savedElement);
		
//		オブジェクトのid部分をDOM要素と照らし合わせる
		if(elementDate.id){
			
//			要素を取得
			const element = document.getElementById(elementDate.id)
			
//			あればそこにクラスを付与する
			if(element){
				element.classList.add('active');
			}
		}
		console.log(elementDate);
	}else{
		console.log("データが存在しません");
		
//		初期設定はすべてボタンにクラスを付与するようにする
		document.getElementById('allButton').classList.add('active');
	}
})

//抽出ボタン一覧対象に処理
buttons.forEach(button =>{
	button.addEventListener('click' , (event)=>{
//		選択した要素で変数
		const target = event.target;
		
		
//		オブジェクトの生成
		const elementDate = {
//			タグ名
			tagName: target.tagName,
			id:target.id || null,
			classList: [...target.classList] || null
		};
		
//		JSONで保存するときの形式の設定　nullはreplace　2はインデントのためのスペース指定
		const jsonElementDate = JSON.stringify(elementDate, null, 2);
		
//		
		console.log(jsonElementDate);
		
//		ローカルストレージに保存
		localStorage.setItem('clickedElement', jsonElementDate);
	})
})

document.querySelector('.logout').addEventListener('click', function(){
//	ローカルストレージに保存しているデータを削除
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



