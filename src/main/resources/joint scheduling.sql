--1. Bom 表
CREATE TABLE `bom_lcfc`(
  `id` BIGINT (20) COMMENT 'ID' PRIMARY KEY,
  `pn` VARCHAR (255) COMMENT '物料号',
  `pn_des` VARCHAR (255) COMMENT '物料描述',
  `plant` VARCHAR (255) COMMENT '',
  `item` VARCHAR (255) COMMENT '',
  `component` VARCHAR (255) COMMENT '组件',
  `component_des` VARCHAR (255) COMMENT '组件描述',
  `compponent_qty` BIGINT COMMENT '组件数量',
  `unit` VARCHAR (255) COMMENT '单位',
  `alt_item_group` VARCHAR (255) COMMENT '替代组',
  `priority` int COMMENT '优先级',
  `usage_probability` VARCHAR (255) COMMENT '替代料配比',
  `create_by` VARCHAR (255) COMMENT '创建人',
  `update_by` VARCHAR (255) COMMENT '修改人',
  `create_time` DATETIME COMMENT '创建时间',
  `update_time` DATETIME COMMENT '修改时间',
  `remark` VARCHAR (255) COMMENT '备注'
) ENGINE = InnoDB DEFAULT charset = utf8 COMMENT 'lcfc的bom';
--2. 物料描述表  material_description
CREATE TABLE `material_description`(
  `id` BIGINT (20) COMMENT 'ID' PRIMARY KEY,
  `pn` VARCHAR (255) PRIMARY KEY COMMENT '物料号',
  `pn_des` VARCHAR (255) COMMENT '物料描述',
  `plant` VARCHAR (255) COMMENT '',
  `project_name` VARCHAR (255) COMMENT '',
  `model` VARCHAR (255) COMMENT '',
  `material_group` VARCHAR (255) COMMENT '',
  `purchasing_group` VARCHAR (255) COMMENT '采购组',
  `create_by` VARCHAR (255) COMMENT '创建人',
  `update_by` VARCHAR (255) COMMENT '修改人',
  `create_time` DATETIME COMMENT '创建时间',
  `update_time` DATETIME COMMENT '修改时间',
  `remark` VARCHAR (255) COMMENT '备注' -- 创建人 修改人  创建时间  修改时间
) ENGINE = InnoDB DEFAULT charset = utf8 COMMENT '物料描述';
--3. 物料qouta&Cost
CREATE TABLE `material_allocation`(
  `id` BIGINT (20) COMMENT 'ID' PRIMARY KEY,
  `pn` VARCHAR (255) PRIMARY KEY COMMENT '物料号',
  `pn_des` VARCHAR (255) COMMENT '物料描述',
  `vendor` VARCHAR (255) COMMENT '供应商',
  `quota` VARCHAR (255) COMMENT '',
  `cost` VARCHAR (255) COMMENT '',
  `create_by` VARCHAR (255) COMMENT '创建人',
  `update_by` VARCHAR (255) COMMENT '修改人',
  `create_time` DATETIME COMMENT '创建时间',
  `update_time` DATETIME COMMENT '修改时间',
  `remark` VARCHAR (255) COMMENT '备注' -- 创建人 修改人  创建时间  修改时间
) ENGINE = InnoDB DEFAULT charset = utf8 COMMENT '物料分配';
--4. 物料需求表  material_demands   ????
CREATE TABLE `material_demands`(
  -- 物料号   物料数量   供应商
  `id` BIGINT (20) PRIMARY KEY COMMENT 'ID',
  `ips_id` VARCHAR (255) COMMENT '缺料版本',
  `pn` VARCHAR (255) COMMENT '物料号',
  `pn_des` VARCHAR (255) COMMENT '物料描述',
  `shortage_qty ` BIGINT COMMENT '所缺数量',
  `demand_type` VARCHAR (255) COMMENT '需求类别 ' `create_by` VARCHAR (255) COMMENT '创建人',
  `update_by` VARCHAR (255) COMMENT '修改人',
  `create_time` DATETIME COMMENT '创建时间',
  `update_time` DATETIME COMMENT '修改时间',
  `remark` VARCHAR (255) COMMENT '备注'
) ENGINE = InnoDB DEFAULT charset = utf8 COMMENT '物料需求';
--5 material库存
CREATE TABLE `material_inv_qty`(
  -- 物料号   物料数量   供应商
  `id` BIGINT (20) PRIMARY KEY COMMENT 'ID',
  `vendor` VARCHAR (255) COMMENT '供应商',
  `pn` VARCHAR (255) COMMENT '物料号',
  `pn_des` VARCHAR (255) COMMENT '物料描述',
  `qty ` BIGINT COMMENT '库存数量',
  `inv_type` VARCHAR (255) COMMENT '库存类别',
  `create_by` VARCHAR (255) COMMENT '创建人',
  `update_by` VARCHAR (255) COMMENT '修改人',
  `create_time` DATETIME COMMENT '创建时间',
  `update_time` DATETIME COMMENT '修改时间',
  `remark` VARCHAR (255) COMMENT '备注'
) ENGINE = InnoDB DEFAULT charset = utf8 COMMENT '物料库存数量';