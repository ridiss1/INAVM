window.onload = function(){
var ramdata102 = [
{
value: 0,
color:'#c4e3f3',
highlight: '#FF5A5E',
label: 'RAM Usage'
},
{
value: 268435456,
color: 'darkblue',
highlight: '#5AD3D1',
label: 'RAM Total'
}
];
var cpudata102 = [
{
value: 0,
color:'#c4e3f3',
highlight: '#FF5A5E',
label: 'CPU Usage'
},
{
value: 1,
color: 'darkblue',
highlight: '#5AD3D1',
label: 'CPU Total'
}
];
var memdata102 = [
{
value: 0,
color:'#c4e3f3',
highlight: '#FF5A5E',
label: 'Disk Usage'
},
{
value: 5368709120,
color: 'darkblue',
highlight: '#5AD3D1',
label: 'Disk Total'
}
];
var ctx = document.getElementById('ramdata102').getContext('2d');
window.myPieramdata102 = new Chart(ctx).Pie(ramdata102);
var ctx = document.getElementById('cpudata102').getContext('2d');
window.myPiecpudata102 = new Chart(ctx).Pie(cpudata102);
var ctx = document.getElementById('memdata102').getContext('2d');
window.myPiememdata102 = new Chart(ctx).Pie(memdata102);
};
