
n = 18;
a = randi([1 13], n, n);

a(a > 10) = 0;

fileID = fopen('teste1.xml','w');

fprintf(fileID, '<?xml version="1.0" encoding="UTF-8"?>\n');
fprintf(fileID, '<!DOCTYPE simulation SYSTEM "simulation.dtd">\n');
fprintf(fileID, '<simulation finalinst="300.0" antcolsize="200" plevel="0.5">\n');
fprintf(fileID, '	<graph nbnodes="%d" nestnode="1">\n',n);
for i = 1:n-1
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
fprintf(fileID, '		<move alpha="1.0" beta="1.0" delta="0.2"/>\n');
fprintf(fileID, '		<evaporation eta="2.0" rho="10.0"/>\n');
fprintf(fileID, '	</events>\n');
fprintf(fileID, '</simulation>\n');

fclose(fileID);
