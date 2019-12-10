function createPie(selector, data) {
	
	const width = $(selector).width();
    const height = $(selector).width();
    let count = 0;
    let avg = 0;
    for(let i=0; i<data.length; i++){
    	avg += data[i].name*data[i].value;
    	count += data[i].value;
    }
    avg = Math.round(avg/count);
    
    console.log(avg+"\t avg");
    console.log(count+"\t count");
    
    // .arc() 새로운 기본값의 아치(호) 생성
	const arc = d3.arc().innerRadius(height/4.5).outerRadius(Math.min(width, height) / 2);

	// 새로운 기본값의 파이 모양의 생성
    const pie = d3.pie()
        .sort((a, b) => b.name - a.name)
        .value(d => d.value);

    const arcs = pie(data);

    const svg = d3.select(selector).append('svg').style('width', width).style('height', height)
    .attr('text-anchor', 'middle')
	.style('font-size', '12px sans-serif'); // 텍스트의 정렬 폰트 설정합니다
    
    // 차트를 그릴 그룹 엘리먼트를 추가합니다.
    const g = svg.append('g')
        .attr('transform', `translate(${width/2}, ${height/2})`);
    // 위치값을 각각 2로 나누는건 반지름 값을 기준으로 한바퀴 돌며 path를 그리기 때문인거 같습니다.
    
    const text = g.append("text");
    
    text.append("tspan")
	    .attr('x', 0)
	    .attr('y', -10)
	    .attr('id', "pathInfo")
	    .style('font-size', '1.5em')
	    .style('font-weight', 'bold')
	    .text("info");
    
    text.append("tspan")
    	.attr('x', 0)
    	.attr('y', 10)
    	.style('font-size', '1.2em')
    	.text(avg+"%");
    
    text.append("tspan")
		.attr('x', 0)
		.attr('y', 30)
		.style('font-size', '1.2em')
		.text("("+count+")");
    
    g.selectAll('path')
        .data(arcs)
        .enter().append('path')
        .attr('fill', d => d.data.color)
        .attr('stroke', 'white')
        .attr('d', arc)
        .attr("title",d => `${d.data.name}`);
    
    const $pathInfo = $("#pathInfo");
    $("path").hover(function() {
		let $this = $(this);
		let title = $this.attr("title");
    	let fill = $this.attr("fill");
    	
    	$pathInfo.text(title).css("color",fill);
		
		
	});
}