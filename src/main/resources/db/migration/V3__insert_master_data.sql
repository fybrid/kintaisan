INSERT INTO departments (department_id, department_name, created_at, updated_at)
VALUES
  (1, '開発部', now(), now()),
  (2, '営業部', now(), now()),
  (3, '人事部', now(), now())
ON CONFLICT DO NOTHING;

INSERT INTO workplaces (workplace_id, workplace_name, created_at, updated_at)
VALUES
  (1, '本社', now(), now()),
  (2, '自宅', now(), now()),
  (3, '支社', now(), now())
ON CONFLICT DO NOTHING;
