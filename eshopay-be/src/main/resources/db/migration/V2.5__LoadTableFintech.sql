--1.insert all data fintech
insert into fintech.fintechs (fint_code,fint_name,fint_short_name,fint_type)
values
('002',	'Bank Rakyat Indonesia', 'BRI', 'BANK'),
('008',	'Bank Mandiri',	'MANDIRI', 'BANK'),
('009',	'Bank Negara Indonesia', 'BNI'	, 'BANK'),
('014',	'Bank Central Asia', 'BCA', 'BANK'),
('022',	'Bank CIMB Niaga',	'CIMB', 'BANK'),
('028',	'Bank OCBC NISP',	'OCBC', 'BANK'),
('426',	'Bank Mega',	'MEGA', 'BANK'),
('013',	'Bank Permata',	'PERMATA', 'BANK'),
('011',	'Bank Danamon',	'DANAMON', 'BANK'),
('016',	'Bank Maybank Indonesia',	'MAYBANK', 'BANK'),
('019',	'Bank Panin',	'PANIN', 'BANK'),
('023',	'Bank UOB Indonesia',	'UOB', 'BANK'),
('200',	'Bank Tabungan Negara', 'BTN', 'BANK'),
('045',	'Bank BTPN/Jago',	'BTPN/JAGO', 'BANK'),
('213',	'Bank BJB',	'BJB', 'BANK'),
('490',	'Bank Neo Commerce','NEO', 'BANK'),
('501',	'Bank Digital BCA (blu)','BCA DIGITAL', 'BANK'),
('503',	'Bank Jago',	'JAGO', 'BANK'),
('506',	'Bank Seabank',	'BANK','BANK'),
('890',	'Dana',	'DANA','E-Wallet'),
('991',	'OVO',	'OVO','E-Wallet'),
('992',	'LinkAja','LINKAJA',	'E-Wallet'),
('993',	'GoPay','GOPAY',	'E-Wallet'),
('994',	'ShopeePay','SHOPEEPAY',	'E-Wallet'),
('995',	'Flip',	'FLIP','P-GateAway'),
('998',	'Jenius (BTPN)	Digital Banking','JENIUS','E-Wallet');

--2. set for bank & fintech sequence range 100

alter sequence fintech.accounts_account_id_seq
restart with 100 increment by 1;

INSERT INTO person.users (user_id, user_name, user_email, user_password, user_handphone, created_date, modified_date)
VALUES
(1, 'SUPRD', 'pascale@gmail.com', '$2a$12$mFFpKZ/85hJvKkBMOWRoeN2vvNf5wWHmSj8WdSACAjnsXQLCa', '(071) 23 67 22', '2025-04-19 20:51:14.562', NULL),
(2, 'PARIS', 'marie@gmail.com', '$2a$12$pjugEuBw6oBDd0fyYmFODRgOjnTmHgJObZAzvWFM0Yyc/p/dZi', '(1) 42 34 22 66', '2025-04-19 20:51:14.562', NULL),
(3, 'FISSA', 'diego@gmail.com', '$2a$12$kEkvEJlzuuKIedtFTqIOFDwsp7sgpSSiHeCoGxS9HYCUJC', '(91) 555-84 44', '2025-04-19 20:51:14.562', NULL),
(4, 'VICTE', 'mary@gmail.com', '$2a$12$MEvcvSHDCdMJFynMtojpCD3Vk/SSYxxZZBo/FpMuH4TuW08rp3BS', '78.32.54.86', '2025-04-19 20:51:14.562', NULL),
(5, 'THEBI', 'liz@gmail.com', '$2a$12$HZtvN8Fgft1pf3kyRH1jp.PqbOx44kAudvNrtmyP.HO4AqM40Ztf', '(509) 555-3612', '2025-04-19 20:51:14.562', NULL),
(6, 'GODOS', 'jose@gmail.com', '$2a$12$dOP6GuhNhjdEraJOY6ydUBT13drvEtJVl6ECgYtVgv4OcKOtTEy', '(95) 555 82 82', '2025-04-19 20:51:14.562', NULL),
(7, 'GOURL', 'andre@gmail.com', '$2a$12$R7y8pIYK66Z1P3SajChriOTzwsZIoQ1pjurX5CwaOiGrwXm9eHK', '(11) 555-9482', '2025-04-19 20:51:14.562', NULL),
(8, 'CHIVO', 'pere@gmail.com', '$2a$12$erUzUNkv1Y2RPUjRRa7yuUv54UOJmqmBIyNuZOjjAzL9748O', '(907) 555-7584', '2025-04-19 20:51:14.562', NULL),
(9, 'BLOND', 'frédérique@gmail.com', '$2a$12$JUqg9v2ksjELNLOFEm0uHFVifJOLmi5ggDYAx/q3nhySUoYspFK', '88.60.15.31', '2025-04-19 20:51:14.562', NULL),
(10, 'OCEAN', 'yvonne@gmail.com', '$2a$12$hTuDxtkRS8NBofnKZDSuPRMaaeEjxtWbW2RbWtA/A3NEwu', '(1) 135-5333', '2025-04-19 20:51:14.562', NULL),
(11, 'MAGAA', 'simon@gmail.com', '$2a$12$4v5Efm1gBLBDFhZCTuH5K5o2zhXSDkC6Im1zdjFm978jr6aLAvbK', '(91) 555-7733', '2025-04-19 20:51:14.562', NULL),
(12, 'TORTU', 'miguel@gmail.com', '$2a$12$dHykbDtugPIkagZ.6ZfXqu1KfCrHEyLKlT9xXNjZM2USUFpm2n96', '(5) 555-2933', '2025-04-19 20:51:14.562', NULL),
(13, 'WHITC', 'winante@gmail.com', '$2a$12$J7Rfb36zJKqkTJm20325JieTN7NEir5gJDltfA2EnxCV8N2y1S', '(262)-555721', '2025-04-19 20:51:14.562', NULL),
(14, 'LAZYK', 'john@gmail.com', '$2a$12$J.Yll69U2V6pubgB6vSOv/lKH-gnZsjh7OwndFArCpdHJf4ba.ua', '(509) 555-7969', '2025-04-19 20:51:14.562', NULL),
(15, 'CACTU', 'patricio@gmail.com', '$2a$12$m5wOoGeuCW1w05RfIpOxb31.zalNeufVBIGVrjRC9pXBSy', '(1) 135-5555', '2025-04-19 20:51:14.562', NULL),
(16, 'BERGS', 'christina@gmail.com', '$2a$12$JBUtjFO5vOON6VLY1ru3lJOueDRMd2FrivnVs2sh5mf6mFdoYxm', '09221-12 34 65', '2025-04-19 20:51:14.562', NULL),
(17, 'DUMON', 'janine@gmail.com', '$2a$12$DmW7ZVjpw4QhWv3roHsjbdsFfyWS2fib.4P+D51uKCfe6RDN.u', '40.67.88.88', '2025-04-19 20:51:14.562', NULL),
(18, 'LINOD', 'anabela@gmail.com', '$2a$12$8EmPRoTnTu58un74MiOkDF9edsFM0Gy4NeVNP6lA-oW+3q', '(11) 555-2167', '2025-04-19 20:51:14.562', NULL),
(19, 'TRAIH', 'helvetius@gmail.com', '$2a$12$Ka7Dlra8dtaCEE.4CNpD4cM4UNigTD553YjLOeyu9R5fBeHz.K', '(206) 555-8257', '2025-04-19 20:51:14.562', NULL),
(20, 'PICCO', 'georg@gmail.com', '$2a$12$w1uNKL19R.kjNVTKVrDrvDPpB2DaBG.mKQKuB2prycN4.Es', '6562-9722', '2025-04-19 20:51:14.562', NULL),
(21, 'SEVES', 'heri@gmail.com', '$2a$12$4Xvnm.S5ZvSuFBCNje3PMO.avm5EtVS9TT7N2kr1xeopXSbzVW', '(91) 745-7717', '2025-04-19 20:51:14.562', NULL),
(22, 'BONAP', 'laurence@gmail.com', '$2a$12$KguiCuPedduvRS3WbCBOctutkzAnYrCMimX8cO7RFMnRymju2C', '91.24.45.40', '2025-04-19 20:51:14.562', NULL),
(23, 'LILAS', 'karla@gmail.com', '$2a$12$LwaNTFAiCFA.ODzZHJeANwLrOeupNmCX25OWDSd23uwtDbuCz', '(91) 555984', '2025-04-19 20:51:14.562', NULL),
(24, 'CENTC', 'francisco@gmail.com', '$2a$12$rxvrBhAZsr1.toyOeN/eod4EBUBeq.vW7aoR2piOIdVWT.65', '(5) 555-3392', '2025-04-19 20:51:14.562', NULL),
(25, 'LINOD', 'felipe@gmail.com', '$2a$12$.FyqSeK9jIVBFngJBwgXDhf5YfB6wZVDup2xrtmbr7kzaHPostLa', '(8) 34-56-12', '2025-04-19 20:51:14.562', NULL),
(26, 'ISLAT', 'fran@gmail.com', '$2a$12$WWB9CChw9mL2g4jR5Xn.g3di2qn5s24UZoCoS6WLp64LaOrO', '(415) 555-9573', '2025-04-19 20:51:14.562', NULL),
(27, 'MEREP', 'jean@gmail.com', '$2a$12$JwVP56Qq4Q35OCTtkX.1odsqYeiINVtI0K7dIFGsIOlQrIpRZvI', '(514) 555-8054', '2025-04-19 20:51:14.562', NULL),
(28, 'OTTIK', 'henriette@gmail.com', '$2a$12$k3x5UjRvRRdm9u6UBoOEF4nVM4LdCRaPaLNaWhXWtXW', '(21) 555-4252', '2025-04-19 20:51:14.562', NULL),
(29, 'QUEDE', 'catherine@gmail.com', '$2a$12$dmBbq3nYpbkNvAvrVD1c.HgqrQErrr3plHF2Im3M79DCRvfuMoa', '40.32.21.67', '2025-04-19 20:51:14.562', NULL),
(30, 'ROMEY', 'alejandra@gmail.com', '$2a$12$Dz7as15ejUdR5kTMztbupVPpDsgTBS5fjpnzLeitRDN/+VHVTqe', '(91) 745-6200', '2025-04-19 20:51:14.562', NULL),
(31, 'VAFFE', 'christin@gmail.com', '$2a$12$LRLokxUdBCbP9hyglZ0ZXeeMtBUrDRh96uB1XAbfbwcMuFKdL6', '0921-12 34 65', '2025-04-19 20:51:14.562', NULL),
(32, 'SANTG', 'jonas@gmail.com', '$2a$12$dZlhNup5ghJuSIABXOq0hmHVNlwvY+AhYNNaVyhV/AnVP2qgBmC', '07.98 92.35', '2025-04-19 20:51:14.562', NULL),
(33, 'EASTC', 'ann@gmail.com', '$2a$12$i3hyf0A7Ufs2VoWHo06p.rxfpdEr2QimXC7rP0bS5mytAh.LmwEq', '(171) 555-0297', '2025-04-19 20:51:14.562', NULL),
(34, 'WELLI', 'sven@gmail.com', '$2a$12$fjPiNeuvo8x2OBECflyJeN7QKIP7fTUHW59tGd5SLT9P.gYvGAtC', '0152-555723', '2025-04-19 20:51:14.562', NULL),
(35, 'FRANR', 'carine@gmail.com', '$2a$12$Ru9g/gWo1DCToRTCJR2e9ymVWhvmEBR.3lmHsWpvC4nBrwdSS', '40.32.21.21', '2025-04-19 20:51:14.562', NULL),
(36, 'FRANS', 'paolo@gmail.com', '$2a$12$97JoulOBsGXam7F+Y5DkUv2l51YSVPLT.7br/pepFJv6DFJvBXfa', '(11) 642-7012', '2025-04-19 20:51:14.562', NULL),
(37, 'GREAL', 'howard@gmail.com', '$2a$12$qKaGxs2bqbqS3HgFjpeisqsikRDazvppWl3A2CFYikKT9Cd98C', '(503) 555-7555', '2025-04-19 20:51:14.562', NULL),
(38, 'SIMOB', 'jytte@gmail.com', '$2a$12$L6UN3udym31sQ1pg632.EY8t4YlfrYbwHrvqTNfmubEbuumy.dFaa', '31 12 34 56', '2025-04-19 20:51:14.562', NULL),
(39, 'MORGK', 'giovanni@gmail.com', '$2a$12$7O2YeecrvtauWsK.0RYW73413P2Y7mnmFYmpIOQM2uLKn1nwjdEC', '088-4603250', '2025-04-19 20:51:14.562', NULL),
(40, 'BLAUS', 'hanna@gmail.com', '$2a$12$T3y/IX0ck5CLGDbnK3m2mvuFtzh2UT07tjIu.yB4MsCqa3oLs5jmK', '0621-08460', '2025-04-19 20:51:14.562', NULL),
(41, 'COMMI', 'horst@gmail.com', '$2a$12$PNToiWYA3x/GumjegDhr.dtdUBB3H6RwLdOvejrfm5ULv8RdG', '0372-035188', '2025-04-19 20:51:14.562', NULL),
(42, 'ANATR', 'ana@gmail.com', '$2a$12$HFZK8O2juOfOME6vauNimwHbqyi5nYTO.xm7/kHuLBeG.P2i', '(5) 555-4729', '2025-04-19 20:51:14.562', NULL),
(43, 'WHITC', 'karl@gmail.com', '$2a$12$sS1HCQe2Irsx9oAy2YkEFoLotx4r4OiGaFsdKROyOz0f.5EzCO3q', '(208) 555-8115', '2025-04-19 20:51:14.562', NULL);

--3. bank account
insert into fintech.accounts (account_no,balance,currency,user_id,fin_code)
values
('77712345678',20000000,'IDR',37,'014'),
('88898765432',10000000,'IDR',38,'008'),
('54298765432 ',5000000,'IDR',39,'993'),
('88912345678 ',3000000,'IDR',40,'994');

--4. set for bank & fintech sequence range 300
alter sequence fintech.accounts_account_id_seq
restart with 300 increment by 1;

--5. user account
insert into fintech.accounts (account_no,balance,currency,user_id,fin_code)
values
('008-133465789',1000000,'IDR',36,'008'),
('014-567898765',2000000,'IDR',36,'014'),
('993-456789876',0,'IDR',36,'993'),
('008-321123456',1000000,'IDR',38,'008'),
('014-876567890',2000000,'IDR',38,'008'),
('994-456789876',20000,'IDR',38,'994');


--6. insert into accounts
-- insert into fintech.accounts (account_no,balance,currency,user_id,fin_code)
-- values
-- ('018-133465789',1000000,'IDR',66,'008'),
-- ('013-567898765',2000000,'IDR',66,'014'),
-- ('993-456789876',0,'IDR',66,'993'),
-- ('008-321123456',1000000,'IDR',68,'008'),
-- ('024-876567890',2000000,'IDR',68,'008'),
-- ('994-456789876',20000,'IDR',68,'994');

