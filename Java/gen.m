
n = 30;
a = randi([1 30], n, n);

%a(a > 29) = 0;

fileID = fopen('test_1.xml','w');

fprintf(fileID, '<?xml version="1.0" encoding="UTF-8"?>\n');
fprintf(fileID, '<!DOCTYPE simulation SYSTEM "simulation.dtd">\n');
fprintf(fileID, '<simulation finalinst="500.0" antcolsize="10000" plevel="15">\n');
fprintf(fileID, '	<graph nbnodes="%d" nestnode="1">\n',n);
for i = 1:n
    fprintf(fileID, '		<node nodeidx="%d">\n', i);
    for j = i+1:n
        if( a(i,j) ~= 0)
           fprintf(fileID, '			<weight targetnode="%d">%d</weight>\n', j, a(i,j));
        end
    end
    fprintf(fileID, '		</node>\n');
end
fprintf(fileID, '	</graph>\n');

fprintf(fileID, '	<events>\n');
fprintf(fileID, '		<move alpha="1.0" beta="0.1" delta="0.1"/>\n');
fprintf(fileID, '		<evaporation eta="15.0" rho="10.0"/>\n');
fprintf(fileID, '	</events>\n');
fprintf(fileID, '</simulation>\n');


fclose(fileID);

b = a;
for i = 1:n
    b(i,i) = 0;
    for j = 1:n
        b(i,j) = b(j,i);
    end
end

ratio = length(b(b>0))/(n*n);

