window.onload = function(){
var ramdata201 = [
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
var cpudata201 = [
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
var memdata201 = [
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
var ctx = document.getElementById('ramdata201').getContext('2d');
window.myPieramdata201 = new Chart(ctx).Pie(ramdata201);
var ctx = document.getElementById('cpudata201').getContext('2d');
window.myPiecpudata201 = new Chart(ctx).Pie(cpudata201);
var ctx = document.getElementById('memdata201').getContext('2d');
window.myPiememdata201 = new Chart(ctx).Pie(memdata201);
var ramdata204 = [
{
value: 20836352,
color:'#c4e3f3',
highlight: '#FF5A5E',
label: 'RAM Usage'
},
{
value: 536870912,
color: 'darkblue',
highlight: '#5AD3D1',
label: 'RAM Total'
}
];
var cpudata204 = [
{
value: 0.310260782108777,
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
var memdata204 = [
{
value: 404033536,
color:'#c4e3f3',
highlight: '#FF5A5E',
label: 'Disk Usage'
},
{
value: 10737418240,
color: 'darkblue',
highlight: '#5AD3D1',
label: 'Disk Total'
}
];
var ctx = document.getElementById('ramdata204').getContext('2d');
window.myPieramdata204 = new Chart(ctx).Pie(ramdata204);
var ctx = document.getElementById('cpudata204').getContext('2d');
window.myPiecpudata204 = new Chart(ctx).Pie(cpudata204);
var ctx = document.getElementById('memdata204').getContext('2d');
window.myPiememdata204 = new Chart(ctx).Pie(memdata204);
};
