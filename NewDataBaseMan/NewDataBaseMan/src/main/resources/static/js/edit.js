function search(event)
{
	const droparea = document.getElementById("circuit_image");
	droparea.style.background = "#E0E0E0";
	event.preventDefault();
}

function droping(event)
{
	event.preventDefault();
	const file = event.dataTransfer.files[0];
	const reader = new FileReader();
	reader.onload = function(){
		const thumb = document.getElementById('thumb');
		thumb.src = reader.result;
	};
	reader.readAsDataURL(file);
}

function progress()
{
	let special=document.getElementById("special_box");
	let chap=special.className;
	switch(parseInt(chap))
	{
		case 0:
		{
			let start_box = document.createElement('div');
			start_box.id="start_box"
			start_box.innerHTML="R×<input type='number' id='R' max='10' min='0' value='0'>C×<input type='number' id='C' max='10' min='0' value='0'>L×<input type='number' id='L' max='10' min='0' value='0'><input type='button' id='formula_start' value='OK' onclick='formula_make();'>"
			document.getElementById("right-side").appendChild(start_box);
			special.innerHTML="回路に使用している抵抗R，コンデンサC，コイルLそれぞれの個数を下のフォームで入力してください<br>入力したら確認ボタンを押してください<br>※使える素子は合計10個までです"
			special.className++;
			break;
		}
		case 1:
		{
			special.innerHTML="それぞれの式を入力してください<br>1つの式に1つ以上の時間変数tを含ませてください<br>式に用いることができる変数や、定数、三角関数などは画面右下の緑のエリアに表示されています";
			let element_box=document.getElementById("element_box");
			let element="使える変数は<br>";
			let R=parseInt(document.getElementById("R").value);
			let C=parseInt(document.getElementById("C").value);
			let L=parseInt(document.getElementById("L").value);
			
			for(i=0;i<R;i++)
			{
				element=element+"R"+String(i+1)+",";
			}
			for(i=0;i<C;i++)
			{
				element=element+"C"+String(i+1)+",";
			}
			for(i=0;i<L;i++)
			{
				element=element+"L"+String(i+1)+",";
			}
			element=element+"<br>和は+、積は*、商は/、べき乗は^、自然対数の底(ネイピア数)はe、<br>xの平方根は√(x)、正弦はsin(x)、余弦はcos(x)で表します。<br>また、負の数は[]で囲み、差を表現するときはa+[b]のようにしてください。";
			element_box.innerHTML=element;
		}
	}
}

function formula_make()
{
	let R=parseInt(document.getElementById("R").value);
	let C=parseInt(document.getElementById("C").value);
	let L=parseInt(document.getElementById("L").value);
	
	if(R+C+L>10)
	{
		alert("使用する素子の数が多すぎます\n合計10個以下になるように設定してください");
	}
	else if(R+C+L==0)
	{
		alert("素子が1つも使われていません");
	}
	else
	{
		let i;
		
		let formula_box = document.createElement('div');
		formula_box.id="formula_box";
		let html="";
		let formula_number=2;

		html=html+"<div id='formula_div_0' class='textbox'><div class='textarea'>V<sub>0</sub>(t)=<input type='text' id='formula_0'></div><input type='button' value='+' id='formula_go_btn_0' onclick='formula_register(event);'></div>"
		html=html+"<div id='formula_div_1' class='textbox'><div class='textarea'>I<sub>0</sub>(t)=<input type='text' id='formula_1'></div><input type='button' value='+' id='formula_go_btn_1' onclick='formula_register(event);'></div>"
		
		for(i=0;i<R;i++)
		{
			html=html+"<div id='formula_div_"+formula_number+"' class='textbox'><div class='textarea'>V<sub>R"+(i+1)+"</sub>(t)=<input type='text' id='formula_"+formula_number+"'></div><input type='button' value='+' id='formula_go_btn_"+formula_number+"' onclick='formula_register(event);'></div>"
			formula_number++;
			html=html+"<div id='formula_div_"+formula_number+"' class='textbox'><div class='textarea'>I<sub>R"+(i+1)+"</sub>(t)=<input type='text' id='formula_"+formula_number+"'></div><input type='button' value='+' id='formula_go_btn_"+formula_number+"' onclick='formula_register(event);'></div>"
			formula_number++;
		}
		
		for(i=0;i<C;i++)
		{
			html=html+"<div id='formula_div_"+formula_number+"' class='textbox'><div class='textarea'>V<sub>C"+(i+1)+"</sub>(t)=<input type='text' id='formula_"+formula_number+"'></div><input type='button' value='+' id='formula_go_btn_"+formula_number+"' onclick='formula_register(event);'></div>"
			formula_number++;
			html=html+"<div id='formula_div_"+formula_number+"' class='textbox'><div class='textarea'>I<sub>C"+(i+1)+"</sub>(t)=<input type='text' id='formula_"+formula_number+"'></div><input type='button' value='+' id='formula_go_btn_"+formula_number+"' onclick='formula_register(event);'></div>"
			formula_number++;
		}
		
		for(i=0;i<L;i++)
		{
			html=html+"<div id='formula_div_"+formula_number+"' class='textbox'><div class='textarea'>V<sub>L"+(i+1)+"</sub>(t)=<input type='text' id='formula_"+formula_number+"'></div><input type='button' value='+' id='formula_go_btn_"+formula_number+"' onclick='formula_register(event);'></div>"
			formula_number++;
			html=html+"<div id='formula_div_"+formula_number+"' class='textbox'><div class='textarea'>I<sub>L"+(i+1)+"</sub>(t)=<input type='text' id='formula_"+formula_number+"'></div><input type='button' value='+' id='formula_go_btn_"+formula_number+"' onclick='formula_register(event);'></div>"
			formula_number++;
		}
		document.getElementById("R").readOnly=true;
		document.getElementById("C").readOnly=true;
		document.getElementById("L").readOnly=true;
		document.getElementById("start_box").removeChild(document.getElementById("formula_start"));
		formula_box.innerHTML=html;
		formula_box.className=(formula_number-1);
		document.getElementById("right-side").appendChild(formula_box);
		
		let element_box = document.createElement('div');
		element_box.id="element_box";
		element_box.innerHTML="";
		document.getElementById("right-side").appendChild(element_box);
		
		progress();
	}
}

function formula_register(event)
{
	let number=event.target.id.replace("formula_go_btn_","");
	let formula_data=document.getElementById("formula_"+number);
	let formula=formula_data.value;
	
	let checking=formula.replace(/[\[\]√]/g,"");

	let formula_check=checking.split(/[\/\*\+\^]/);
	if(event.target.value=="+")
	{
		let element_nothing=false;
		let t_nothing=true;
		let element_error=false;
		let formula_empty=false;
		let root_error=false;
		let element;
		let R_n=document.getElementById("R").value;
		let C_n=document.getElementById("C").value;
		let L_n=document.getElementById("L").value;
		for(let i=0;i<formula_check.length;i++)
		{
			if(formula_check[i]=="")
			{
				element_nothing=true;
			}
			else if(formula_check[i].replace(/\(*[RCL]1?[0-9]\)*/,"")=="")
			{
				element=formula_check[i].replace(/[\(\)]/g,"");
				switch(element.replace(/1?[0-9]/,""))
				{
					case "R":
					{
						if(!(parseInt(element.replace("R",""))<=parseInt(R_n)&&parseInt(element.replace("R",""))>parseInt(0)))
						{
							element_error=true;
						}
						break;
					}
					case "C":
					{
						if(!(parseInt(element.replace("C",""))<=parseInt(C_n)&&parseInt(element.replace("C",""))>parseInt(0)))
						{
							element_error=true;
						}
						break;
					}
					case "L":
					{
						if(!(parseInt(element.replace("L",""))<=parseInt(L_n)&&parseInt(element.replace("L",""))>parseInt(0)))
						{
							element_error=true;				
						}
						break;
					}
					default:
					{
						element_error=true;
					}
				}
			}
			else if(formula_check[i].replace(/\(*t\)*/,"")=="")
			{
				t_nothing=false;
			}
			else if(formula_check[i].replace(/\(*[1-9][0-9\.]*\)*/,"")!="")
			{
				if(formula_check[i].replace(/\(*e\)*/,"")!=""&&formula_check[i].replace(/\(*sin\(.+\)*/,"")!=""&&formula_check[i].replace(/\(*cos\(.+\)*/,"")!="")
				{
					element_error=true;
				}
			}
		}
		if(formula=="")
		{
			formula_empty=true;
		}
		
		let root_check=formula.split("√");
		for(i=1;i<root_check.length;i++)
		{
			if(root_check[i].charAt(0)!="(")
			{
				root_error=true;
			}
		}
		
		if(element_nothing!=true&&element_error!=true&&formula_empty!=true&&t_nothing!=true&&root_error!=true)
		{
			let e_formula=formula;
			
			let R=parseInt(document.getElementById("R").value);
			let C=parseInt(document.getElementById("C").value);
			let L=parseInt(document.getElementById("L").value);
			
			e_formula=e_formula.replace(/t/g,"1");
			e_formula=e_formula.replace(/√/g,"Math.sqrt");
			e_formula=e_formula.replace(/sin/g,"Math.sin");
			e_formula=e_formula.replace(/cos/g,"Math.cos");
			e_formula=e_formula.replace(/\^/g,"**");
			e_formula=e_formula.replace(/e/g,"Math.E");
			e_formula=e_formula.replace(/\+\[/g,"-");
			e_formula=e_formula.replace(/\[/g,"-");
			e_formula=e_formula.replace(/\]/g,"");
			
			for(i=0;i<R;i++)
			{
				e_formula=e_formula.replace(new RegExp("R"+String(i+1),"g"),"1");
			}
			
			for(i=0;i<C;i++)
			{
				e_formula=e_formula.replace(new RegExp("C"+String(i+1),"g"),"1");
			}
			
			for(i=0;i<L;i++)
			{
				e_formula=e_formula.replace(new RegExp("L"+String(i+1),"g"),"1");
			}
			
			try
			{
				eval(e_formula);
				formula_data.readOnly=true;
				event.target.value="-";
			}
			catch(e)
			{
				alert("eval_error");
			}
		}
		else
		{
			if(formula_empty==true)
			{
				alert("formula_empty");
			}
			if(element_nothing==true)
			{
				alert("element_nothing");
			}
			if(element_error==true)
			{
				alert("element_error");
			}
			if(t_nothing==true)
			{
				alert("t_nothing");
			}
			if(root_error==true)
			{
				alert("root_error");
			}
		}
	}
	else if(event.target.value=="-")
	{
		formula_data.readOnly=false;
		event.target.value="+";
	}
}

function save(event)
{
	const num=parseInt(document.getElementById("formula_box").className);
	const img=document.getElementById("thumb").src;
	let data="";
	let i;
	data=img;
	for(i=0;i<num;i++)
	{
		data=data+","+document.getElementById("formula_"+i).value;
	}
	alert(data);
}

function openload(event)
{
	let data=document.getElementById("test").value.split(',');
	let img=document.getElementById("thumb");
	let i;
	let formula="";
	img.src=data[0];
	for(i=0;i<data.length-1;i++)
	{
		formula=formula+"<div id='formula_text_"+i+"' class='textbox'><div class='textarea'><input type='text' id='formula_"+i+"' value='"+data[i+1]+"' readonly='readonly'></div><input type='button' id='formula_delete_"+i+"' value='-' onclick='formula_delete(event);'></div>";
	}
	formula=formula+"<div id='formula_text_"+(data.length-1)+"' class='textbox'><div class='textarea'><input type='text' id='formula'></div><input type='button' id='formula_go_btn' value='+' onclick='formula_register();'></div>";
	let box=document.getElementById("formula_box");
	box.className=parseInt(data.length-1);
	box.innerHTML=formula;
}

function upload_edit(event) {
	
	let formula_error=false;
	for(let i=0;i<=document.getElementById("formula_box").className;i++)
	{
		if(document.getElementById("formula_go_btn_"+i).value!="-")
		{
			formula_error=true;
		}
	}
	
	if(formula_error!=true)
	{
		let formData = new FormData($('#edit_form').get()[0]);
		formData.append("ex_name",$("#ex_name").val());
		formData.append("ex_comment",$("#ex_comment").val());
		
		let element_box=document.getElementById("element_box");
		let box=element_box.innerHTML.split("<br>");
		let element=box[1].split(",");
		
		let formula="V0(t)="+document.getElementById("formula_0").value;
		formula=formula+",I0(t)="+document.getElementById("formula_1").value;
		
		for(i=2;i<=document.getElementById("formula_box").className;i+=2)
		{
				formula=formula+",V"+element[(i-2)/2]+"(t)="+document.getElementById("formula_"+i).value;
				formula=formula+",I"+element[(i-2)/2]+"(t)="+document.getElementById("formula_"+(i+1)).value;
		}
		
		formData.append("formula",formula);
		
		alert(formula)
		
		alert("clicked");
		
		$.ajax({
			url : "/upload_edit",
			type : "post",
			data : formData,
			processData: false,
			contentType: false,
			cache:false
	　　　　}).done(function(resData, textStatus, jqXHR) {
			window.alert("s");
	　　　　}).fail(function(resData, textStatus, jqXHR) {
			window.alert("f");
	　　　　});
	}
	else
	{
		alert("formula_error");
	}
}
