export function createPie(selector, data, size) {
	
	const width = size;
    const height = size;

	const arc = d3.arc().innerRadius(60).outerRadius(Math.min(width, height) / 2);
    // .arc() 새로운 기본값의 아치(호) 생성
    // .innerRadius() 안쪽 반지름 값, 0이면 완전한 원이되고 값이 있으면 도넛 형태가 됩니다.
    // .outerRadius() 바깥쪽 반지름값

    const pie = d3.pie()
    // 새로운 기본값의 파이 모양의 생성
        .sort((a, b) => b.name - a.name)
        .value(d => d.value);

    const arcs = pie(data);

    const svg = d3.select(selector).append('svg').style('width', width).style('height', height);

    const g = svg.append('g')
        .attr('transform', `translate(${width/2}, ${height/2})`);
    // 우선 차트를 그릴 그룹 엘리먼트를 추가합니다.
    // 위치값을 각각 2로 나누는건 반지름 값을 기준으로 한바퀴 돌며 path를 그리기 때문인거 같습니다.

    g.selectAll('path')
        .data(arcs)
        .enter().append('path')
        .attr('fill', d => d.data.color)
        .attr('stroke', 'white')
        .attr('d', arc)
}