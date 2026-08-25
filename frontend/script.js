const cities = ['Bareilly','Delhi','Varanasi','Pune'];
const colors = {'Bareilly':'#00ff00','Delhi':'#ff0000','Varanasi':'#9933ff','Pune':'#0000ff'};
const refreshBtn = document.getElementById('refresh');
const updated = document.getElementById('updated');
const cardsDiv = document.getElementById('cards');

let charts = {};

async function fetchData(){
  cardsDiv.innerHTML = 'Loading...';
  try{
    const res = await fetch('/data');


    const data = await res.json();
    cardsDiv.innerHTML = '';

    cities.forEach(city=>{
      const card = document.createElement('div');
      card.className='card';
      const w = data[city].weather.hourly;
      const a = data[city].air.hourly;
      card.innerHTML=`<div class='card-title'>${city}</div>
        <div class='card-value'>Rain: ${w.precipitation.slice(-1)[0]} mm</div>
        <div class='card-value'>UV: ${w.uv_index.slice(-1)[0]}</div>
        <div class='card-value'>AQI: ${a.aqi.slice(-1)[0]}</div>
        <div class='card-value'>PM2.5: ${a.pm2_5.slice(-1)[0]}</div>`;
      cardsDiv.appendChild(card);
    });

    updated.textContent = 'Last Updated: '+new Date().toLocaleTimeString();
    updateCharts(data);
  }catch(e){
    cardsDiv.innerHTML='⚠️ Could not fetch data';
    console.error(e);
  }
}

function createChart(ctx, label){
  return new Chart(ctx, {
    type:'line',
    data:{ labels: Array.from({length:24},(_,i)=>i+'h'), datasets:[] },
    options:{ responsive:true, plugins:{ legend:{ position:'top' }, title:{ display:true, text:label } } }
  });
}

function updateCharts(data){
  const metrics = ['temperature','humidity','windspeed','precipitation','uv_index','aqi','pm2_5'];
  const chartIds = ['temperatureChart','humidityChart','windspeedChart','rainfallChart','uvChart','aqiChart','pm25Chart'];

  metrics.forEach((metric,i)=>{
    if(!charts[metric]) charts[metric] = createChart(document.getElementById(chartIds[i]), metric);
    charts[metric].data.datasets = [];

    cities.forEach(city=>{
      let values;
      const w = data[city].weather.hourly;
      const a = data[city].air.hourly;

      if(metric==='temperature') values = w.temperature_2m.slice(-24);
      else if(metric==='humidity') values = w.humidity_2m.slice(-24);
      else if(metric==='windspeed') values = w.windspeed_10m.slice(-24);
      else if(metric==='precipitation') values = w.precipitation.slice(-24);
      else if(metric==='uv_index') values = w.uv_index.slice(-24);
      else if(metric==='aqi') values = a.aqi.slice(-24);
      else if(metric==='pm2_5') values = a.pm2_5.slice(-24);

      charts[metric].data.datasets.push({ label: city, data: values, borderColor: colors[city], fill:false });
    });

    charts[metric].update();
  });
}

refreshBtn.addEventListener('click', fetchData);
setInterval(fetchData, 300000); // auto-refresh every 5 min
fetchData();
