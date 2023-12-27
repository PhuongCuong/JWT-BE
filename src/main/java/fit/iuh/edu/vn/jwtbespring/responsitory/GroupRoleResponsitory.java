package fit.iuh.edu.vn.jwtbespring.responsitory;

import fit.iuh.edu.vn.jwtbespring.modules.GroupRole;
import fit.iuh.edu.vn.jwtbespring.pks.GroupRolePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRoleResponsitory extends JpaRepository<GroupRole, GroupRolePK> {
}
