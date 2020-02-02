function formula()
{
	let formula_data=document.getElementById("formula_data").innerHTML;
	formula_data=formula_data.replace(/\+\[/g,"-");
	formula_data=formula_data.replace(/\[/g,"-");
	formula_data=formula_data.replace(/\]/g,"");
	let formula=formula_data.split(',');
	let box=document.getElementById("formula_box");
	let element_box=document.getElementById("element_box");
	let elen;
	let element;
	let ht="";
	let i;
	let j;
	
	for (i = 0; i < formula.length; i++)
	{
		ht=ht+"<div id='formula_text_"+i+"' class='textbox'><div class='textarea'><input type='text' id='formula_"+i+"' value='"+formula[i]+"' readonly='readonly'></div><input type='button' id='formula_Go_"+i+"' value='Go' onclick='openPopup();'></div>"
		if(i>1&&i%2==0)
		{
			elen=document.createElement('div');
			element=formula[i].substr(1, 3).replace("(","");
			elen.innerHTML=element+"<input type='text' id='element_"+(i/2)+"' name='"+element+"'>";
			element_box.appendChild(elen);
		}
	}
	box.innerHTML = ht;
}
function openPopup() {
	let popup=document.createElement('div');
	let data=event.target.id.replace("formula_Go_","");
	let formula=document.getElementById("formula_"+data).value;
	popup.id="js-popup";
	popup.className="popup";
	html="<div class='popup-inner'><canvas id='myLineChart'></canvas></div>";
	html=html+"<div class='black-background' id='js-black-bg' onclick='closePopup();'></div>";
	popup.innerHTML=html;
	document.getElementById("god").appendChild(popup);
	charting(formula);
}
function closePopup() {
	document.getElementById("js-popup").parentNode.removeChild(document.getElementById("js-popup"));
}
function charting(formula)
{
	let result=formula.split("=");
	formula=formula.replace(result[0]+"=","");
	alert(formula);
	let i=1;
	let element;
	
	while(document.getElementById("element_"+i)!=null)
	{
		element=document.getElementById("element_"+i);
		formula=formula.replace(new RegExp(element.name,"g"),element.value);
		i++;
	}
	formula=formula.replace(/t/g,"x");
	formula=formula.replace(/e/g,"Math.E");
	formula=formula.replace(/√/g,"Math.sqrt");
	formula=formula.replace(/\^/g,"**");
	formula=formula.replace(/sin/g,"Math.sin");
	formula=formula.replace(/cos/g,"Math.cos");
	
	alert(formula);
	
	let ctx = document.getElementById("myLineChart");
	let data=new Array(1000);
	let time = new Array(1000);
	for(let t=0;t<200;t++)
	{
		data[t]=eval(formula.replace(/x/g,t*0.05));
		time[t]=t;
	}
	let myLineChart = new Chart(ctx, {
	    type: 'line',
	    data: {
	      labels: time,
	      datasets: [
	        {
	          label: '',
	          data: data,
	          borderColor: "rgba(255,0,0,1)",
	          backgroundColor: "rgba(0,0,0,0)"
	        }
	      ],
	    },
	    options: {
	      title: {
	        display: true,
	        text: ''
	      },
	      scales: {
	        yAxes: [{
	          ticks: {
	            suggestedMax:10,
	            suggestedMin: 0,
	            stepSize: 5,
	            callback: function(value, index, values){
	              return  value +  '度'
	            }
	          }
	        }]
	      },
	    }
	  });
}