insert into drive_user (id, name) values ('3fe79e6a-6811-4e53-8b38-3cd85f8c6106', 'user 1');
insert into drive_user (id, name) values ('2b2fc7d2-ef1a-422d-bcf0-159f67278006', 'user 2');

insert into entry (id, owner_id, name, description) values ('4a2c7839-03b4-4024-b541-e31e26df88ed', '3fe79e6a-6811-4e53-8b38-3cd85f8c6106', 'test entry name', 'test entry desc');

insert into entry_item (entry_id, path, type) values ('4a2c7839-03b4-4024-b541-e31e26df88ed', '/', 'DIRECTORY');

insert into entry_item (entry_id, path, type) values ('4a2c7839-03b4-4024-b541-e31e26df88ed', '/dir', 'DIRECTORY');
insert into entry_item (entry_id, path, type) values ('4a2c7839-03b4-4024-b541-e31e26df88ed', '/dir/sub', 'DIRECTORY');
insert into entry_item (entry_id, path, type) values ('4a2c7839-03b4-4024-b541-e31e26df88ed', '/dir/ab', 'DIRECTORY');
insert into entry_item (entry_id, path, type) values ('4a2c7839-03b4-4024-b541-e31e26df88ed', '/file1', 'FILE');
insert into entry_item (entry_id, path, type) values ('4a2c7839-03b4-4024-b541-e31e26df88ed', '/secret.txt', 'FILE');

insert into entry_item (entry_id, path, type) values ('4a2c7839-03b4-4024-b541-e31e26df88ed', '/dir/ab/file1', 'FILE');
insert into entry_item (entry_id, path, type) values ('4a2c7839-03b4-4024-b541-e31e26df88ed', '/dir/ab/file2', 'FILE');
